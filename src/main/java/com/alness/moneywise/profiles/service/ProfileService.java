package com.alness.moneywise.profiles.service;

import java.util.List;
import java.util.Map;

import com.alness.moneywise.common.dto.CommonResponse;
import com.alness.moneywise.profiles.dto.request.ProfileRequest;
import com.alness.moneywise.profiles.dto.response.ProfileResponse;

public interface ProfileService {
    public List<ProfileResponse> find(Map<String, String> parameters);
    public ProfileResponse findOne(String id);
    public ProfileResponse save(ProfileRequest request);
    public ProfileResponse update(String id, ProfileRequest request);
    public CommonResponse delete(String id);
}
