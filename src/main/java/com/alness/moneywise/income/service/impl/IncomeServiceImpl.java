package com.alness.moneywise.income.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.income.dto.request.IncomeRequest;
import com.alness.moneywise.income.dto.response.IncomeResponse;
import com.alness.moneywise.income.service.IncomeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService{

    @Override
    public List<IncomeResponse> find(Map<String, String> params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public IncomeResponse findOne(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public IncomeResponse save(IncomeRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public IncomeResponse update(String id, IncomeRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CommonResponse delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
