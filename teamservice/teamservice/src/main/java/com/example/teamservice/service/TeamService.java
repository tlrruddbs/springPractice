package com.example.teamservice.service;

import com.example.teamservice.domain.dto.TeamCreateData;
import com.example.teamservice.domain.dto.TeamResponseData;
import com.example.teamservice.domain.entity.Team;
import com.example.teamservice.domain.entity.TeamMember;
import com.example.teamservice.repository.TeamMemberRepository;
import com.example.teamservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    //팀을 생성한다.
    public TeamResponseData save(TeamCreateData teamCreateData){
        Team team = Team.builder()
                .name(teamCreateData.getName())
                .address(teamCreateData.getAddress())
                .build();

        team = teamRepository.save(team);

        return TeamResponseData.builder()
                .teamId(team.getId())
                .name(team.getName())
                .address(team.getAddress())
                .build();
    }

    public void addTeamMember(Long userId, String teamName){
        Team selectTeam = teamRepository.findByName(teamName);

        TeamMember teamMember = TeamMember.builder()
                .team(selectTeam)
                .userId(userId)
                .build();

        TeamMember teamM = teamMemberRepository.save(teamMember);
        System.out.println(teamM.getId());
        System.out.println(teamM.getUserId());
    }

    //사용자 번호로 팀을 반환한다
    public TeamResponseData getTeamByUserId(Long userId){
        TeamMember teamMember = teamMemberRepository.findByUserId(userId);

        System.out.println(teamMember.getId());
        System.out.println(teamMember.getUserId());
        System.out.println(teamMember.getTeam());

        Team team = teamMember.getTeam();

        return TeamResponseData.builder()
                .teamId(team.getId())
                .name(team.getName())
                .address(team.getAddress())
                .build();
    }
}
