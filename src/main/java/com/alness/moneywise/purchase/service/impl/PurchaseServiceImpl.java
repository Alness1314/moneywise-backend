package com.alness.moneywise.purchase.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
import com.alness.moneywise.purchase.dto.request.PurchaseRequest;
import com.alness.moneywise.purchase.dto.response.PurchaseResponse;
import com.alness.moneywise.purchase.entity.PurchaseEntity;
import com.alness.moneywise.purchase.repository.PurchaseRepository;
import com.alness.moneywise.purchase.service.PurchaseService;
import com.alness.moneywise.purchase.specification.PurchaseSpecification;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

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

        mapper.createTypeMap(PurchaseRequest.class, PurchaseEntity.class)
                .addMappings(
                        mpa -> mpa.using(localDateConverter).map(PurchaseRequest::getPurchaseDate,
                                PurchaseEntity::setPurchaseDate));
    }

    @Override
    public List<PurchaseResponse> find(Map<String, String> params) {
        Specification<PurchaseEntity> specification = filterWithParameters(params);
        return purchaseRepository.findAll(specification)
                .stream()
                .map(this::mapperDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseResponse findOne(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public PurchaseResponse save(PurchaseRequest request) {
        PurchaseEntity newPurchase = mapper.map(request, PurchaseEntity.class);
        try {
            newPurchase.setMonthlyPayment(calculateMonthlyPayment(request.getMount(), request.getDeadlines()));
            newPurchase = purchaseRepository.save(newPurchase);
        } catch (Exception e) {
            log.error("Error to save purchase: {}", e.getMessage());
            throw new GlobalExceptionHandler("P-422", HttpStatus.UNPROCESSABLE_ENTITY, "Error to save purchase.");
        }

        return mapperDto(newPurchase);
    }

    private PurchaseResponse mapperDto(PurchaseEntity source) {
        return mapper.map(source, PurchaseResponse.class);
    }

    public Specification<PurchaseEntity> filterWithParameters(Map<String, String> parameters) {
        return new PurchaseSpecification().getSpecificationByFilters(parameters);
    }

    @Override
    public PurchaseResponse update(String id, PurchaseRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CommonResponse delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal mount, Integer deadlines) {
        if (deadlines == 0) {
            throw new ArithmeticException("Deadlines cannot be zero");
        }
        return mount.divide(BigDecimal.valueOf(deadlines), RoundingMode.HALF_UP);
    }

}
