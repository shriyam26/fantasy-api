package com.fantasy.contestapi.schemaobject;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveContestSo {

    private String homeTeamId;
    private String awayTeamId;
    private List<PlayerPointsSo> playerPointsSoList;
    private LocalDateTime matchTime;
    private String winnerId;
    private String participationFee; //This is to be kept fixed 40
}
