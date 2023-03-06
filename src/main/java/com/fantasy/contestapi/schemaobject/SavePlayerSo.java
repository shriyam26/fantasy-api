package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class SavePlayerSo {
    private String playerId;
    private String playerName;
    private String totalPoints;
    private String wins;
    private String netAmount;
}
