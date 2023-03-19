package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class SearchPlayerResponseSo {
    private Long id;
    private String playerName;
    private String email;
    private String totalPoints;
    private String wins;
    private String netAmount;
    private Long previousSeasonWins;
    private Long previousSeasonPoints;
    private Long previousSeasonAmount;
}
