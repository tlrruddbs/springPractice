package com.example.userservice.controller;

import com.example.userservice.domain.data.UserCreateData;
import com.example.userservice.domain.data.UserResponseData;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public UserResponseData createUser(@RequestBody UserCreateData userCreateData){
        System.out.println("users");
        return userService.save(userCreateData);
    }

    @GetMapping("/users/{userId}")
    public UserResponseData getUser(@PathVariable("userId") Long id){
        System.out.println("userId");
        return userService.getUserById(id);
    }
}
