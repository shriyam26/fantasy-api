package com.fantasy.contestapi.schemaobject;

import lombok.Data;

@Data
public class ResultSo {
    private Long id;
    private String points;
    private String amountEarned;
    private Long position;
    private String playerName;
}
