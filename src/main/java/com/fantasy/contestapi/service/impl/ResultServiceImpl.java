package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.entity.Result;
import com.fantasy.contestapi.mapper.ResultToPointRewardsResultSoMapper;
import com.fantasy.contestapi.repository.ResultRepository;
import com.fantasy.contestapi.schemaobject.PerformanceResultSo;
import com.fantasy.contestapi.schemaobject.PointRewardsResultSo;
import com.fantasy.contestapi.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public List<PerformanceResultSo> getPerformanceResults(List<Long> playerIds) {
        List<Result> resultList = resultRepository.findByPlayerIdIn(playerIds);
        List<PerformanceResultSo> performanceResultSoList = new ArrayList<>();
        Map<String, List<Result>> resultPlayerMap = resultList.stream().collect(Collectors.groupingBy(result -> result.getPlayer().getPlayerName()));
        resultPlayerMap.forEach((playerName, playerResultList) -> {
            PerformanceResultSo performanceResultSo = new PerformanceResultSo();
            List<PointRewardsResultSo> pointRewardsResultSos = new ArrayList<>();
            playerResultList.forEach(playerResult -> pointRewardsResultSos.add(ResultToPointRewardsResultSoMapper.map(playerResult)));
            performanceResultSo.setPlayerName(playerName);
            performanceResultSo.setPointRewardsResultSos(pointRewardsResultSos);
            performanceResultSoList.add(performanceResultSo);
        });
        return performanceResultSoList;
    }
}
