package com.alness.moneywise.users.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alness.moneywise.profiles.dto.response.ProfileResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String username;
    private String password;
    private DetailResponse detailUser;
    private List<ProfileResponse> profiles;
    private LocalDateTime createdAt;
    private Boolean enabled;
}
