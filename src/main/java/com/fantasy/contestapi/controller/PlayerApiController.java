package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import com.fantasy.contestapi.service.SavePlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PlayerApiController implements PlayerApi {

    private final SavePlayerService savePlayerService;

    @Override
    public void savePlayer(List<SavePlayerSo> savePlayerSoList) {
        savePlayerService.savePlayer(savePlayerSoList);
    }
}
