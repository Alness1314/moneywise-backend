package com.alness.moneywise.income.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.common.utils.Formatters;
import com.alness.moneywise.exceptions.GlobalExceptionHandler;
import com.alness.moneywise.exceptions.NotFoundException;
import com.alness.moneywise.income.dto.request.IncomeRequest;
import com.alness.moneywise.income.dto.response.IncomeResponse;
import com.alness.moneywise.income.entity.IncomeEntity;
import com.alness.moneywise.income.repository.IncomeRepository;
import com.alness.moneywise.income.service.IncomeService;
import com.alness.moneywise.income.specification.IncomeSpecification;
import com.alness.moneywise.users.entity.UserEntity;
import com.alness.moneywise.users.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<String, LocalDate> localDateConverter = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                if (source == null) {
                    return null;
                } else {
                    return Formatters.parseLocalDate(source);
                }
            }
        };

        mapper.createTypeMap(IncomeRequest.class, IncomeEntity.class)
                .addMappings(
                        mpa -> mpa.using(localDateConverter).map(IncomeRequest::getPaymentDate,
                                IncomeEntity::setPaymentDate));
    }

    @Override
    public List<IncomeResponse> find(Map<String, String> params) {
        Specification<IncomeEntity> specification = filterWithParameters(params);
        return incomeRepository.findAll(specification)
                .stream()
                .map(this::mapperDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncomeResponse findOne(String id) {
        IncomeEntity income = incomeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Income not found."));
        return mapperDto(income);
    }

    @Override
    public IncomeResponse save(IncomeRequest request) {
        IncomeEntity income = mapper.map(request, IncomeEntity.class);
        UserEntity user = userRepository.findById(UUID.fromString(request.getUser()))
                .orElseThrow(() -> new NotFoundException("User not found."));
        income.setUsuario(user);
        try {
            income = incomeRepository.save(income);
        } catch (Exception e) {
            log.error("Error to save income {}", e.getMessage());
            e.printStackTrace();
            throw new GlobalExceptionHandler("P-422", HttpStatus.UNPROCESSABLE_ENTITY, "Error to save income.");
        }
        return mapperDto(income);
    }

    @Override
    public IncomeResponse update(String id, IncomeRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CommonResponse delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private IncomeResponse mapperDto(IncomeEntity source) {
        return mapper.map(source, IncomeResponse.class);
    }

    public Specification<IncomeEntity> filterWithParameters(Map<String, String> parameters) {
        return new IncomeSpecification().getSpecificationByFilters(parameters);
    }

}
