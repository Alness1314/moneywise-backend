package com.alness.moneywise.expenses.controller;

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
import com.alness.moneywise.expenses.dto.request.ExpensesRequest;
import com.alness.moneywise.expenses.dto.response.ExpensesResponse;
import com.alness.moneywise.expenses.service.ExpensesService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/expenses")
@Tag(name = "Expenses", description = ".")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;

    @GetMapping()
    public ResponseEntity<List<ExpensesResponse>> findAll(@RequestParam Map<String, String> parameters) {
        List<ExpensesResponse> response = expensesService.find(parameters);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpensesResponse> findOne(@PathVariable String id) {
        ExpensesResponse response = expensesService.findOne(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<ExpensesResponse> save(@Valid @RequestBody ExpensesRequest request) {
        ExpensesResponse response = expensesService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpensesResponse> update(@PathVariable String id, @RequestBody ExpensesRequest request) {
        ExpensesResponse response = expensesService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable String id) {
        CommonResponse response = expensesService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
