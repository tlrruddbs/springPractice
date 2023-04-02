package com.example.teamservice.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamCreateData {
    private String name;
    private String address;
}
