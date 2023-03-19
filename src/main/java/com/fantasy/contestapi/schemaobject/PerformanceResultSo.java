package com.fantasy.contestapi.schemaobject;

import lombok.Data;

import java.util.List;

@Data
public class PerformanceResultSo {

    private String playerName;
    private List<PointRewardsResultSo> pointRewardsResultSos;
}
