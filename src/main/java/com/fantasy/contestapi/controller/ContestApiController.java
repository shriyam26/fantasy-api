package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SaveContestSo;
import com.fantasy.contestapi.service.SaveContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContestApiController implements ContestApi {

    private final SaveContestService saveContestService;

    @Override
    public void saveContest(SaveContestSo saveContestSo) {
        saveContestService.saveContest(saveContestSo);
    }
}