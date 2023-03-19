package com.fantasy.contestapi.schemaobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AmendContestSo {

    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private String winnerCount;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime matchTime;
    private String contestValue;
}
