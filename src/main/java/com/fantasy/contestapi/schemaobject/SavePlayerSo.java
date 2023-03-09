package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class SavePlayerSo {
    private String playerName;
    private Long previousSeasonWins;
    private Long previousSeasonPoints;
    private Long previousSeasonAmount;
}
