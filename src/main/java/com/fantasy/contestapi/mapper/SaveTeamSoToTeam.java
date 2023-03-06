package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Team;
import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTeamSoToTeam {

    public static Team map(SaveTeamSo saveTeamSo) {
        Team team = new Team();
        BeanUtils.copyProperties(saveTeamSo, team);
        return team;
    }
}
