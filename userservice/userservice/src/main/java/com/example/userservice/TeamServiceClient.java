package com.example.userservice;

import com.example.userservice.domain.data.TeamResponseData;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "team-service")
public interface TeamServiceClient {
//    @GetMapping("/{userId}/teams")
//    public TeamResponseData getTeam(@PathVariable("userId") Long id);

    @GetMapping("/{userId}/teams")
    public TeamResponseData getTeam(@PathVariable("userId") Long id);
}
