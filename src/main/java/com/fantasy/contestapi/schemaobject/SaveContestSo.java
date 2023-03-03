package com.fantasy.contestapi.schemaobject;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveContestSo {

    private String homeTeam;
    private String awayTeam;
    private List<PlayerPointsSo> playerPointsSoList;
    private LocalDateTime matchTime;
    private String winner;
}
