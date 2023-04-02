package com.example.userservice.service;

import com.example.userservice.TeamServiceClient;
import com.example.userservice.domain.data.TeamResponseData;
import com.example.userservice.domain.data.UserCreateData;
import com.example.userservice.domain.data.UserResponseData;
import com.example.userservice.domain.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final TeamServiceClient teamServiceClient;

    //사용자 저장
    public UserResponseData save(UserCreateData userCreateData){
        User user = User.builder()
                .userName(userCreateData.getUserName())
                .build();
        user = userRepository.save(user);

        return UserResponseData.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .team(null)
                .build();

    }

    //사용자 조회
    public UserResponseData getUserById(Long id){
        User userOptional = userRepository.findById(id).orElseThrow(RuntimeException::new);

//        String url = String.format("http://team-service/%s/teams", id);
//        ResponseEntity<TeamResponseData> responseData = restTemplate.exchange(url, HttpMethod.GET, null, TeamResponseData.class);
//        TeamResponseData team = responseData.getBody();
        try {
            TeamResponseData team = teamServiceClient.getTeam(id);

            return UserResponseData.builder()
                    .userId(userOptional.getId())
                    .userName(userOptional.getUserName())
                    .team(team)
                    .build();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
