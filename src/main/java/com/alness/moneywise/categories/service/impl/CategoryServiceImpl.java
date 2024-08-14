package com.alness.moneywise.categories.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alness.moneywise.categories.dto.request.CategoryRequest;
import com.alness.moneywise.categories.dto.response.CategoryResponse;
import com.alness.moneywise.categories.entity.CategoryEntity;
import com.alness.moneywise.categories.repository.CategoryRepository;
import com.alness.moneywise.categories.service.CategoryService;
import com.alness.moneywise.categories.specification.CategorySpecification;
import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.exceptions.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper mapper = new ModelMapper();
    
    @Override
    public List<CategoryResponse> find(Map<String, String> params) {
       Specification<CategoryEntity> specification = filterWithParameters(params);
        return categoryRepository.findAll(specification)
                .stream()
                .map(this::mapperDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findOne(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {
        CategoryEntity category = mapper.map(request, CategoryEntity.class);
        try {
            category = categoryRepository.save(category);
        } catch (Exception e) {
            log.error("Error to save category {}", e.getMessage());
            throw new GlobalExceptionHandler("P-422", HttpStatus.UNPROCESSABLE_ENTITY, "Error to save category");
        }
        return mapperDto(category);
    }

    @Override
    public CategoryResponse update(String id, CategoryRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CommonResponse delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    private CategoryResponse mapperDto(CategoryEntity source) {
        return mapper.map(source, CategoryResponse.class);
    }

    public Specification<CategoryEntity> filterWithParameters(Map<String, String> parameters) {
        return new CategorySpecification().getSpecificationByFilters(parameters);
    }
}
