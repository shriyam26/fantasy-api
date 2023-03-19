package com.fantasy.contestapi.constant;

public enum ErrorMessages {

    ERR_PLAYER_INVALID("ERR_PLAYER_INVALID", "Player name cannot be blank"),
    ERR_TEAM_INVALID("ERR_PLAYER_INVALID", "Team name cannot be blank"),
    ERR_CONTEST_ID_INVALID("ERR_CONTEST_ID_INVALID", "Contest id cannot be blank"),
    ERR_INDISTINCT_PLAYER("ERR_INDISTINCT_PLAYER", "Selected players should not be same."),
    ERR_CONTEST_EXISTS("ERR_CONTEST_EXISTS", "Contest already exists for the given home and away team. Please use correct home and away team"),
    ERR_CONTEST_MATCH_TIME_EXISTS("ERR_CONTEST_MATCH_TIME_EXISTS", "Contest already exists for the given match time. Please specify correct match time.");

    public final String code;
    public final String message;

    ErrorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
