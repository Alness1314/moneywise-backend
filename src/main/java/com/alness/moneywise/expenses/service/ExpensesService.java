package com.alness.moneywise.expenses.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.expenses.dto.request.ExpensesRequest;
import com.alness.moneywise.expenses.dto.response.ExpensesResponse;

public interface ExpensesService {
    public List<ExpensesResponse> find(Map<String, String> params);
    public ExpensesResponse findOne(String id);
    public ExpensesResponse save(ExpensesRequest request);
    public ExpensesResponse update(String id, ExpensesRequest request);
    public CommonResponse delete(String id);
}
