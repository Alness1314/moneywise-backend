package com.alness.moneywise.users.controller;

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
import com.alness.moneywise.users.dto.request.UserRequest;
import com.alness.moneywise.users.dto.response.UserResponse;
import com.alness.moneywise.users.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/users")
@Tag(name = "Users", description = "Registration of users to manage access.")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll(@RequestParam Map<String, String> parameters) {
        List<UserResponse> response = userService.find(parameters);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findOne(@PathVariable String id) {
        UserResponse response = userService.findOne(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable String id, @RequestBody UserRequest request){
        UserResponse response = userService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable String id){
        CommonResponse response = userService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
