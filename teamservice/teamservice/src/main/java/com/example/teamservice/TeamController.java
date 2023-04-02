package com.example.teamservice;

import com.example.teamservice.domain.dto.TeamCreateData;
import com.example.teamservice.domain.dto.TeamMemberAddData;
import com.example.teamservice.domain.dto.TeamResponseData;
import com.example.teamservice.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/teams")
    public TeamResponseData create(@RequestBody TeamCreateData teamCreateData){
        return teamService.save(teamCreateData);
    }

    @PostMapping("/{userId}/teams")
    public ResponseEntity addTeamMember(@PathVariable("userId") Long id,
                                        @RequestBody TeamMemberAddData requestData){
        System.out.println(id+", "+requestData.getName());
        teamService.addTeamMember(id, requestData.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/teams")
    public TeamResponseData getTeamByUserId(@PathVariable("userId") Long id){
        System.out.println("{userId}/teams: "+id);
        return teamService.getTeamByUserId(id);
    }


}
