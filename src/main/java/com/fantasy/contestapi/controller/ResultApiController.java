package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.PerformanceResultSo;
import com.fantasy.contestapi.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ResultApiController implements ResultApi {

    private final ResultService resultService;

    @Override
    public ResponseEntity<List<PerformanceResultSo>> getPerformanceResults(List<Long> playerIds) {
        return ResponseEntity.ok(resultService.getPerformanceResults(playerIds));
    }
}
