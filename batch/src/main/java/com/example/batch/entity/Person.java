package com.example.batch.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RedisHash(value="people", timeToLive = 30)
@NoArgsConstructor
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public Person(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
