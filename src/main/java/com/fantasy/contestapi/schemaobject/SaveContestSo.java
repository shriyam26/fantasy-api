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
    private Long winnerId;
}
