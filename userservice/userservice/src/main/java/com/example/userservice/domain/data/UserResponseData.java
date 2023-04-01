package com.example.userservice.domain.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseData {
    private Long userId;
    private String userName;
    private TeamResponseData team;
}
