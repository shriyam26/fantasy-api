package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Team;
import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTeamSoToTeamMapper {

    public static Team map(SaveTeamSo saveTeamSo) {
        Team team = new Team();
        team.setTeamName(saveTeamSo.getTeamName());
        team.setPreviousSeasonPoints(saveTeamSo.getPreviousSeasonPoints());
        team.setPreviousSeasonWins(saveTeamSo.getPreviousSeasonWins());
        return team;
    }
}
