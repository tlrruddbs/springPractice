package com.example.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

@Entity(name = "users")
@Builder
@Getter
@AllArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;
    private String userName;

    protected User(){}
}
