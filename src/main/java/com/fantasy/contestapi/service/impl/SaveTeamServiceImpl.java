package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.entity.Team;
import com.fantasy.contestapi.mapper.SaveTeamSoToTeamMapper;
import com.fantasy.contestapi.repository.TeamRepository;
import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import com.fantasy.contestapi.service.SaveTeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveTeamServiceImpl implements SaveTeamService {

    private final TeamRepository teamRepository;

    @Override
    public void saveTeamService(List<SaveTeamSo> saveTeamSoList) {
        if (saveTeamSoList.stream().anyMatch(saveTeamSo -> StringUtils.isBlank(saveTeamSo.getTeamName()))) {
            //throw error here
            log.error("Please send valid team name");
            return;
        }
        List<Team> teamList = new ArrayList<>();
        saveTeamSoList.forEach(saveTeamSo -> teamList.add(SaveTeamSoToTeamMapper.map(saveTeamSo)));
        teamRepository.saveAll(teamList);
    }
}
