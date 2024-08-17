package com.alness.moneywise.purchase.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.purchase.dto.request.PurchaseRequest;
import com.alness.moneywise.purchase.dto.response.PurchaseResponse;

public interface PurchaseService {
    public List<PurchaseResponse> find(Map<String, String> params);
    public PurchaseResponse findOne(String id);
    public PurchaseResponse save(PurchaseRequest request);
    public PurchaseResponse update(String id, PurchaseRequest request);
    public CommonResponse delete(String id);
}
