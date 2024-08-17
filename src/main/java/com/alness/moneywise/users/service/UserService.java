package com.alness.moneywise.users.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.users.dto.request.UserRequest;
import com.alness.moneywise.users.dto.response.UserResponse;

public interface UserService {
    public List<UserResponse> find(Map<String, String> parameters);
    public UserResponse findOne(String id);
    public UserResponse save(UserRequest request);
    public UserResponse update(String id, UserRequest request);
    public CommonResponse delete(String id);
    public UserResponse findByUsername(String username);
}
