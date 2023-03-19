package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.entity.Result;
import com.fantasy.contestapi.schemaobject.ContestPerformanceSo;
import com.fantasy.contestapi.schemaobject.ResultSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContestToContestPerformanceSoMapper {

    public static ContestPerformanceSo map(Contest contest) {
        ContestPerformanceSo contestPerformanceSo = new ContestPerformanceSo();
        contestPerformanceSo.setContestValue(contest.getContestValue());
        contestPerformanceSo.setId(contest.getId());
        contestPerformanceSo.setMatchTime(contest.getMatchTime());
        contestPerformanceSo.setHomeTeam(contest.getHomeTeam().getTeamName());
        contestPerformanceSo.setAwayTeam(contest.getAwayTeam().getTeamName());
        contestPerformanceSo.setResultsList(contest.getResults().stream().map(ContestToContestPerformanceSoMapper::mapResultToResultSo).collect(Collectors.toList()));
        Optional<Result> winnerResult = contest.getResults().stream().filter(result -> Objects.equals(1L, result.getPosition())).findFirst();
        contestPerformanceSo.setWinnerPlayerName(winnerResult.map(result -> result.getPlayer().getPlayerName()).orElse(null));
        return contestPerformanceSo;
    }

    private static ResultSo mapResultToResultSo(Result result) {
        ResultSo resultSo = new ResultSo();
        BeanUtils.copyProperties(result, resultSo);
        resultSo.setPlayerName(result.getPlayer().getPlayerName());
        return resultSo;
    }
}
