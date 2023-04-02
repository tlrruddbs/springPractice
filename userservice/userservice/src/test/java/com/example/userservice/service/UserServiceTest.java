package com.example.userservice.service;


import com.example.userservice.TeamServiceClient;
import com.example.userservice.domain.data.TeamResponseData;
import com.example.userservice.domain.data.UserResponseData;
import com.example.userservice.domain.entity.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class UserServiceTest {
    private UserService userService;
    private final UserRepository userRepository = mock(UserRepository.class);
    private final TeamServiceClient teamServiceClient = mock(TeamServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository, restTemplate, teamServiceClient);

        User user = User.builder()
                .id(1L)
                .userName("name")
                .build();

        TeamResponseData responseData = TeamResponseData.builder()
                .teamId(1L)
                .name("team name")
                .address("address")
                .build();

        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));

        //feign test
        given(teamServiceClient.getTeam(anyLong())).willReturn(responseData);

        //restTemplate Test
        given(restTemplate.exchange(eq("http://localhost:8000/team/1L/teams"), HttpMethod.GET, null, UserResponseData.class))
                .will(invocation -> ResponseEntity.status(HttpStatus.OK).body(responseData));

    }
}
