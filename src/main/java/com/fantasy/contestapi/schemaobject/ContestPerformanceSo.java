package com.fantasy.contestapi.schemaobject;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContestPerformanceSo {
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private String contestValue;
    private LocalDateTime matchTime;
    private String winnerPlayerName;
    private List<ResultSo> resultsList;
}
