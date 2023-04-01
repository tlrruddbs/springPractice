package com.example.userservice.service;

import com.example.userservice.domain.data.UserCreateData;
import com.example.userservice.domain.data.UserResponseData;
import com.example.userservice.domain.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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

        return UserResponseData.builder()
                .userId(userOptional.getId())
                .userName(userOptional.getUserName())
                .team(null)
                .build();
    }
}
