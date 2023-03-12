package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import com.fantasy.contestapi.schemaobject.SearchPlayerResponseSo;
import com.fantasy.contestapi.service.SavePlayerService;
import com.fantasy.contestapi.service.SearchPlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PlayerApiController implements PlayerApi {

    private final SavePlayerService savePlayerService;
    private final SearchPlayerService searchPlayerService;

    @Override
    public void savePlayer(List<SavePlayerSo> savePlayerSoList) {
        savePlayerService.savePlayer(savePlayerSoList);
    }

    @Override
    public ResponseEntity<List<SearchPlayerResponseSo>> getPlayerList() {
        return ResponseEntity.ok(searchPlayerService.getPlayerList());
    }
}
