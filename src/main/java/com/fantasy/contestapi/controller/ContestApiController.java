package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.schemaobject.AmendContestSo;
import com.fantasy.contestapi.schemaobject.ContestPerformanceSo;
import com.fantasy.contestapi.schemaobject.SaveContestSo;
import com.fantasy.contestapi.service.ContestService;
import com.fantasy.contestapi.service.SaveContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContestApiController implements ContestApi {

    private final SaveContestService saveContestService;
    private final ContestService contestService;

    @Override
    public void saveContest(SaveContestSo saveContestSo) {
        saveContestService.saveContest(saveContestSo);
    }

    @Override
    public void updateContest(List<AmendContestSo> amendContestSo) {
        contestService.updateContestDetails(amendContestSo);
    }

    @Override
    public ResponseEntity<List<Contest>> getContest(List<Long> contestIds) {
        return ResponseEntity.ok(contestService.getContestDetails(contestIds));
    }

    @Override
    public ResponseEntity<List<ContestPerformanceSo>> fetchContestWinnerDetails() {
        return ResponseEntity.ok(contestService.fetchContestWinnerDetails());
    }
}