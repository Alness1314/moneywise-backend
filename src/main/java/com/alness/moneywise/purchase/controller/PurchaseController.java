package com.alness.moneywise.purchase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.purchase.dto.request.PurchaseRequest;
import com.alness.moneywise.purchase.dto.response.PurchaseResponse;
import com.alness.moneywise.purchase.service.PurchaseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/purchase")
@Tag(name = "Purchase", description = ".")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;


    @GetMapping()
    public ResponseEntity<List<PurchaseResponse>> findAll(@RequestParam Map<String, String> parameters) {
        List<PurchaseResponse> response = purchaseService.find(parameters);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> findOne(@PathVariable String id) {
        PurchaseResponse response = purchaseService.findOne(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<PurchaseResponse> save(@Valid @RequestBody PurchaseRequest request) {
        PurchaseResponse response = purchaseService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseResponse> update(@PathVariable String id, @RequestBody PurchaseRequest request){
        PurchaseResponse response = purchaseService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable String id){
        CommonResponse response = purchaseService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
