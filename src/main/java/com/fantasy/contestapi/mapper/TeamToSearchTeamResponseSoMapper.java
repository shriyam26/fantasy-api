package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Team;
import com.fantasy.contestapi.schemaobject.SearchTeamResponseSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamToSearchTeamResponseSoMapper {

    public static SearchTeamResponseSo map(Team team) {
        SearchTeamResponseSo responseSo = new SearchTeamResponseSo();
        BeanUtils.copyProperties(team, responseSo);
        return responseSo;
    }
}
