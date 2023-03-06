package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.mapper.SaveTeamSoToTeam;
import com.fantasy.contestapi.repository.TeamRepository;
import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import com.fantasy.contestapi.service.SaveTeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveTeamServiceImpl implements SaveTeamService {

    private final TeamRepository teamRepository;

    @Override
    public void saveTeamService(SaveTeamSo saveTeamSo) {
        if (StringUtils.isBlank(saveTeamSo.getTeamName())) {
            //throw error here
            log.error("Please send valid team name");
        }
        teamRepository.save(SaveTeamSoToTeam.map(saveTeamSo));
    }
}
