package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import com.fantasy.contestapi.service.SaveTeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TeamApiController implements TeamApi {

    private final SaveTeamService saveTeamService;

    @Override
    public void saveTeam(List<SaveTeamSo> saveTeamSoList) {
        saveTeamService.saveTeamService(saveTeamSoList);
    }
}
