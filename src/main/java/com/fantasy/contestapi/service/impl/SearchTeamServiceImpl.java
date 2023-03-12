package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.entity.Team;
import com.fantasy.contestapi.mapper.TeamToSearchTeamResponseSoMapper;
import com.fantasy.contestapi.repository.TeamRepository;
import com.fantasy.contestapi.schemaobject.SearchTeamResponseSo;
import com.fantasy.contestapi.service.SearchTeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchTeamServiceImpl implements SearchTeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<SearchTeamResponseSo> getTeamList() {
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream().
                map(TeamToSearchTeamResponseSoMapper::map)
                .collect(Collectors.toList());
    }
}
