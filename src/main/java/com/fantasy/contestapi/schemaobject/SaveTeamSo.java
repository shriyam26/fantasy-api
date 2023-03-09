package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class SaveTeamSo {

    private String teamName;
    private Long previousSeasonWins;
    private Long previousSeasonPoints;
}
