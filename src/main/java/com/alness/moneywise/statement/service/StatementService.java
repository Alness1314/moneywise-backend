package com.alness.moneywise.statement.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.statement.dto.request.StatementRequest;
import com.alness.moneywise.statement.dto.response.StatementResponse;

public interface StatementService {
    public List<StatementResponse> find(Map<String, String> params);
    public StatementResponse findOne(String id);
    public StatementResponse save(StatementRequest request);
    public StatementResponse update(String id, StatementRequest request);
    public CommonResponse delete(String id);
}
