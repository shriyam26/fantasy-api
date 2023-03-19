package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class SavePlayerSo {
    private String playerName;
    private String email;
    private Long previousSeasonWins;
    private Long previousSeasonPoints;
    private Long previousSeasonAmount;
}
