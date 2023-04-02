package com.example.teamservice.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponseData {
    private Long teamId;
    private String name;
    private String address;
}
