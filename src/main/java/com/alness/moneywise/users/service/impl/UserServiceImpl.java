package com.alness.moneywise.users.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.exceptions.NotFoundException;
import com.alness.moneywise.profiles.entity.ProfileEntity;
import com.alness.moneywise.profiles.repository.ProfileRepository;
import com.alness.moneywise.users.dto.request.UserRequest;
import com.alness.moneywise.users.dto.response.UserResponse;
import com.alness.moneywise.users.entity.DetailEntity;
import com.alness.moneywise.users.entity.UserEntity;
import com.alness.moneywise.users.repository.UserRepository;
import com.alness.moneywise.users.service.UserService;
import com.alness.moneywise.users.specification.UserSpecification;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileRepository profileRepository;

    private ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public List<UserResponse> find(Map<String, String> parameters) {
        Specification<UserEntity> specification = filterWithParameters(parameters);
        return userRepository.findAll(specification)
                .stream()
                .map(this::mapperDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findOne(String id) {
        UserEntity user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("User not found."));
        return mapperDto(user);
    }

    @Override
    public UserResponse save(UserRequest request) {
        UserEntity newUser = mapper.map(request, UserEntity.class);

        // Inicializar el detalle del usuario
        if (request.getDetailUser() != null) {
            DetailEntity detail = mapper.map(request.getDetailUser(), DetailEntity.class);
            detail.setUser(newUser); // establece la referencia bidireccional
            newUser.setDetailUser(detail);
        }

        List<ProfileEntity> profiles = new ArrayList<>();
        for (String profileName : request.getProfiles()) {
            ProfileEntity profile = profileRepository.findByName(profileName).orElse(null);
            profiles.add(profile);
        }
        newUser.setProfiles(profiles);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            newUser = userRepository.save(newUser);
        } catch (Exception e) {
            log.info("Error saving user: {}", e.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    String.format("Problem with saving user: [%s]", e.getMessage()));
        }
        return mapperDto(newUser);
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CommonResponse delete(String id) {
        UserEntity user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("User to delete not found."));
        try {
            userRepository.delete(user);
            return new CommonResponse("User delete successfully", true);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
            return new CommonResponse("Error to delete user.", false);
        }
    }

    private UserResponse mapperDto(UserEntity source) {
        return mapper.map(source, UserResponse.class);
    }

    public Specification<UserEntity> filterWithParameters(Map<String, String> parameters) {
        return new UserSpecification().getSpecificationByFilters(parameters);
    }

    @Override
    public UserResponse findByUsername(String username) {
        Specification<UserEntity> specification = filterWithParameters(Map.of("username", username, "enabled", "true"));
        UserEntity user = userRepository.findOne(specification)
                .orElse(null);
        if (user != null)
            return mapperDto(user);

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Specification<UserEntity> specification = filterWithParameters(Map.of("username", username, "enabled", "true"));
        UserEntity user = userRepository.findOne(specification).orElseThrow(
                () -> new NotFoundException(String.format("User with name: [%s] not found in database", username)));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getProfiles().forEach(profile -> authorities.add(new SimpleGrantedAuthority(profile.getName())));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
