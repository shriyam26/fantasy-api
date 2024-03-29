package com.fantasy.contestapi.schemaobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveContestSo {

    private Long homeTeamId;
    private Long awayTeamId;
    private List<PlayerPointsSo> playerPointsSoList;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime matchTime;
    //Send winner id as -1 in case of no result
    private Long winnerId;
}
