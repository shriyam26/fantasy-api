package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import com.fantasy.contestapi.schemaobject.SearchTeamResponseSo;
import com.fantasy.contestapi.service.SaveTeamService;
import com.fantasy.contestapi.service.SearchTeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TeamApiController implements TeamApi {

    private final SaveTeamService saveTeamService;
    private final SearchTeamService searchTeamService;

    @Override
    public void saveTeam(List<SaveTeamSo> saveTeamSoList) {
        saveTeamService.saveTeamService(saveTeamSoList);
    }

    @Override
    public ResponseEntity<List<SearchTeamResponseSo>> getTeamList() {
        return ResponseEntity.ok(searchTeamService.getTeamList());
    }
}
