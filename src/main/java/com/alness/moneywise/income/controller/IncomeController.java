package com.alness.moneywise.income.controller;

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
import com.alness.moneywise.income.dto.request.IncomeRequest;
import com.alness.moneywise.income.dto.response.IncomeResponse;
import com.alness.moneywise.income.service.IncomeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/income")
@Tag(name = "Income", description = ".")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping()
    public ResponseEntity<List<IncomeResponse>> findAll(@RequestParam Map<String, String> parameters) {
        List<IncomeResponse> response = incomeService.find(parameters);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponse> findOne(@PathVariable String id) {
        IncomeResponse response = incomeService.findOne(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<IncomeResponse> save(@Valid @RequestBody IncomeRequest request) {
        IncomeResponse response = incomeService.save(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeResponse> update(@PathVariable String id, @RequestBody IncomeRequest request) {
        IncomeResponse response = incomeService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable String id) {
        CommonResponse response = incomeService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
