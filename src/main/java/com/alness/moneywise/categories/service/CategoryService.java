package com.alness.moneywise.categories.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.categories.dto.request.CategoryRequest;
import com.alness.moneywise.categories.dto.response.CategoryResponse;
import com.alness.moneywise.common.dto.CommonResponse;

public interface CategoryService {
    public List<CategoryResponse> find(Map<String, String> params);
    public CategoryResponse findOne(String id);
    public CategoryResponse save(CategoryRequest request);
    public CategoryResponse update(String id, CategoryRequest request);
    public CommonResponse delete(String id);
}
