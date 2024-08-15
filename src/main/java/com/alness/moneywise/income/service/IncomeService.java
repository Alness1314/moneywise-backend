package com.alness.moneywise.income.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.income.dto.request.IncomeRequest;
import com.alness.moneywise.income.dto.response.IncomeResponse;

public interface IncomeService {
    public List<IncomeResponse> find(Map<String, String> params);
    public IncomeResponse findOne(String id);
    public IncomeResponse save(IncomeRequest request);
    public IncomeResponse update(String id, IncomeRequest request);
    public CommonResponse delete(String id);
}
