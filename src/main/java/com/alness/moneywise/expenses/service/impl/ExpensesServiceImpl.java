package com.alness.moneywise.expenses.service.impl;

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

import com.alness.moneywise.categories.entity.CategoryEntity;
import com.alness.moneywise.categories.repository.CategoryRepository;
import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.common.utils.Formatters;
import com.alness.moneywise.exceptions.GlobalExceptionHandler;
import com.alness.moneywise.exceptions.NotFoundException;
import com.alness.moneywise.expenses.dto.request.ExpensesRequest;
import com.alness.moneywise.expenses.dto.response.ExpensesResponse;
import com.alness.moneywise.expenses.entity.ExpensesEntity;
import com.alness.moneywise.expenses.repository.ExpensesRepository;
import com.alness.moneywise.expenses.service.ExpensesService;
import com.alness.moneywise.expenses.specification.ExpensesSpecification;
import com.alness.moneywise.users.entity.UserEntity;
import com.alness.moneywise.users.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

        mapper.createTypeMap(ExpensesRequest.class, ExpensesEntity.class)
                .addMappings(
                        mpa -> mpa.using(localDateConverter).map(ExpensesRequest::getPaymentDate,
                                ExpensesEntity::setPaymentDate));
    }

    @Override
    public List<ExpensesResponse> find(Map<String, String> params) {
        Specification<ExpensesEntity> specification = filterWithParameters(params);
        return expensesRepository.findAll(specification)
                .stream()
                .map(this::mapperDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpensesResponse findOne(String id) {
        ExpensesEntity expenses = expensesRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Expenses not found."));
        return mapperDto(expenses);
    }

    @Override
    public ExpensesResponse save(ExpensesRequest request) {
        ExpensesEntity expenses = mapper.map(request, ExpensesEntity.class);
        UserEntity user = userRepository.findById(UUID.fromString(request.getUser()))
                .orElseThrow(() -> new NotFoundException("User not found."));
        expenses.setUsuario(user);
        CategoryEntity category = categoryRepository.findById(UUID.fromString(request.getCategory()))
                .orElseThrow(() -> new NotFoundException("category not found."));
        expenses.setCategory(category);
        try {
            expenses = expensesRepository.save(expenses);
        } catch (Exception e) {
            log.error("Error to save expenses {}", e.getMessage());
            e.printStackTrace();
            throw new GlobalExceptionHandler("P-422", HttpStatus.UNPROCESSABLE_ENTITY, "Error to save expenses.");
        }
        return mapperDto(expenses);
    }

    @Override
    public ExpensesResponse update(String id, ExpensesRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CommonResponse delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private ExpensesResponse mapperDto(ExpensesEntity expenses) {
        return mapper.map(expenses, ExpensesResponse.class);
    }

    public Specification<ExpensesEntity> filterWithParameters(Map<String, String> parameters) {
        return new ExpensesSpecification().getSpecificationByFilters(parameters);
    }

}
