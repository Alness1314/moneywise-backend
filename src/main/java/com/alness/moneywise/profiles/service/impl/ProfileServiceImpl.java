package com.alness.moneywise.profiles.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.exceptions.NotFoundException;
import com.alness.moneywise.profiles.dto.request.ProfileRequest;
import com.alness.moneywise.profiles.dto.response.ProfileResponse;
import com.alness.moneywise.profiles.entity.ProfileEntity;
import com.alness.moneywise.profiles.repository.ProfileRepository;
import com.alness.moneywise.profiles.service.ProfileService;
import com.alness.moneywise.profiles.specification.ProfileSpecification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepo;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<ProfileResponse> find(Map<String, String> parameters) {
        Specification<ProfileEntity> specification = filterWithParameters(parameters);
        return profileRepo.findAll(specification)
                .stream()
                .map(this::mapperDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileResponse findOne(String id) {
        Specification<ProfileEntity> specification = filterWithParameters(Map.of("id", id, "enabled", "true"));
        ProfileEntity user = profileRepo.findOne(specification)
                .orElseThrow(() -> new NotFoundException("User not found or deactivated."));

        return mapperDto(user);
    }

    @Override
    public ProfileResponse save(ProfileRequest request) {
        ProfileEntity newProfile = mapper.map(request, ProfileEntity.class);
        try {
            newProfile = profileRepo.save(newProfile);
        } catch (Exception e) {
            log.error("error to save profile: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("error to save profile: [%s]", e.getMessage()));
        }
        return mapperDto(newProfile);
    }

    @Override
    public ProfileResponse update(String id, ProfileRequest request) {
        ProfileEntity updateProfile = profileRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        // Actualizar los campos del perfil usando `mapper`
        mapper.map(request, updateProfile);

        try {
            updateProfile = profileRepo.save(updateProfile);
        } catch (Exception e) {
            String errorMessage = "Error updating profile: " + e.getMessage();
            log.error(errorMessage, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }

        return mapperDto(updateProfile);
    }

    @Override
    public CommonResponse delete(String id) {
        ProfileEntity deleteProfile = profileRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        try {
            profileRepo.delete(deleteProfile);
            return new CommonResponse("The profile has been deleted successfully.",true);
        } catch (Exception e) {
            log.error("Error detele profile {}", e.getMessage());
            return new CommonResponse("The profile could not be deleted.",false);
        }

    }

    private ProfileResponse mapperDto(ProfileEntity source) {
        return mapper.map(source, ProfileResponse.class);
    }

    public Specification<ProfileEntity> filterWithParameters(Map<String, String> parameters) {
        return new ProfileSpecification().getSpecificationByFilters(parameters);
    }
}
