package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Result;
import com.fantasy.contestapi.schemaobject.PointRewardsResultSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultToPointRewardsResultSoMapper {

    public static PointRewardsResultSo map(Result result) {
        PointRewardsResultSo pointRewardsResultSo = new PointRewardsResultSo();
        pointRewardsResultSo.setReward(result.getAmountEarned());
        pointRewardsResultSo.setPoints(result.getPoints());
        pointRewardsResultSo.setPosition(result.getPosition());
        pointRewardsResultSo.setHomeTeam(result.getContest().getHomeTeam().getTeamName());
        pointRewardsResultSo.setAwayTeam(result.getContest().getAwayTeam().getTeamName());
        pointRewardsResultSo.setWins(result.getPlayer().getWins());
        return pointRewardsResultSo;
    }
}
