package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class PointRewardsResultSo {

    private String homeTeam;
    private String awayTeam;
    private String points;
    private String reward;
    private String wins;
    private Long position;
}
