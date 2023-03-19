package com.fantasy.contestapi.validation;

import com.fantasy.contestapi.constant.ErrorMessages;
import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.repository.ContestRepository;
import com.fantasy.contestapi.schemaobject.PlayerPointsSo;
import com.fantasy.contestapi.schemaobject.SaveContestSo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContestValidator {

    private final ContestRepository contestRepository;

    public void validate(SaveContestSo saveContestSo) {
        if (!saveContestSo.getPlayerPointsSoList().stream().map(PlayerPointsSo::getPlayerId).allMatch(new HashSet<>()::add)) {
            throw new ContestException(ErrorMessages.ERR_INDISTINCT_PLAYER.getCode(), ErrorMessages.ERR_INDISTINCT_PLAYER.getMessage());
        }
        Contest contest = contestRepository.findContestByHomeTeam_IdAndAwayTeam_Id(saveContestSo.getHomeTeamId(), saveContestSo.getAwayTeamId());
        if (ObjectUtils.isNotEmpty(contest)) {
            throw new ContestException(ErrorMessages.ERR_CONTEST_EXISTS.getCode(), ErrorMessages.ERR_CONTEST_EXISTS.getMessage());
        }
        contest = contestRepository.findContestByMatchTimeEquals(saveContestSo.getMatchTime());
        if (ObjectUtils.isNotEmpty(contest)) {
            throw new ContestException(ErrorMessages.ERR_CONTEST_MATCH_TIME_EXISTS.getCode(), ErrorMessages.ERR_CONTEST_MATCH_TIME_EXISTS.getMessage());
        }
    }

}
