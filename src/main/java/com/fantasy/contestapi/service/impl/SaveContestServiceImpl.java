package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.entity.Result;
import com.fantasy.contestapi.entity.Team;
import com.fantasy.contestapi.repository.ContestRepository;
import com.fantasy.contestapi.repository.PlayerRepository;
import com.fantasy.contestapi.repository.ResultRepository;
import com.fantasy.contestapi.repository.TeamRepository;
import com.fantasy.contestapi.schemaobject.PlayerPointsSo;
import com.fantasy.contestapi.schemaobject.SaveContestSo;
import com.fantasy.contestapi.service.SaveContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveContestServiceImpl implements SaveContestService {

    @Value("${contest.ipl.participationFee:}")
    private final Long participationFee;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final ContestRepository contestRepository;
    private final ResultRepository resultRepository;

    @Override
    public void saveContest(SaveContestSo saveContestSo) {
        List<Team> teamList = updateTeamScore(saveContestSo.getHomeTeamId(), saveContestSo.getAwayTeamId(), saveContestSo.getWinnerId());

        saveContestSo.getPlayerPointsSoList().sort(Comparator.comparing(PlayerPointsSo::getPoints, Comparator.reverseOrder()));
        List<String> playerIds = saveContestSo.getPlayerPointsSoList().stream().map(PlayerPointsSo::getPlayerId).collect(Collectors.toList());
        List<Player> playerList = playerRepository.findByIdIn(playerIds);
        Map<String, PlayerPointsSo> playerPointsSoMap = new HashMap<>();
        saveContestSo.getPlayerPointsSoList().forEach(playerPointsSo -> playerPointsSoMap.put(playerPointsSo.getPlayerId(), playerPointsSo));

        Map<String, Player> playerMap = new HashMap<>();
        playerList.forEach(player -> playerMap.put(String.valueOf(player.getId()), player));

        Long participantCount = playerPointsSoMap.values().stream().filter(playerPointsSo -> "0".equalsIgnoreCase(playerPointsSo.getPoints())).count();
        Long contestNetAmount = getContestNetAmount(participantCount);

        Contest contest = new Contest();
        contest.setHomeTeam(teamList.stream().filter(team -> saveContestSo.getHomeTeamId().equalsIgnoreCase(String.valueOf(team.getId()))).findFirst().orElse(null));
        contest.setAwayTeam(teamList.stream().filter(team -> saveContestSo.getAwayTeamId().equalsIgnoreCase(String.valueOf(team.getId()))).findFirst().orElse(null));
        contest.setMatchTime(saveContestSo.getMatchTime());
        contest.setWinnerCount(String.valueOf(getWinnerCount(participantCount)));
        contest.setContestValue(String.valueOf(participantCount * participationFee));
        contestRepository.save(contest);

        List<Result> resultList = new ArrayList<>();
        List<Player> playerUpdatedList = new ArrayList<>();
        saveContestSo.getPlayerPointsSoList().forEach(playerPointsSo -> {
            Player player = playerMap.get(playerPointsSo.getPlayerId());
            Result result = new Result();
            result.setContest(contest);
            result.setPlayer(player);
            result.setPoints(playerPointsSo.getPoints());
            setPlayerAndResultAmount(player, result, playerPointsSoMap, getWinnerCount(participantCount), contestNetAmount);
            resultList.add(result);
            playerUpdatedList.add(player);
        });
        resultRepository.saveAll(resultList);
        playerRepository.saveAll(playerUpdatedList);
        log.info("Method reached.");
    }

    public List<Team> updateTeamScore(String homeTeamId, String awayTeamId, String winnerId) {
        //Think no result case and nrr case.
        List<Team> teamList = teamRepository.findByIdIn(Arrays.asList(homeTeamId, awayTeamId));
        teamList.stream().filter(team -> winnerId.equalsIgnoreCase(String.valueOf(team.getId()))).forEach(team -> {
            team.setMatches(team.getMatches() + 1);
            team.setPoints(team.getPoints() + 1);
            team.setWins(team.getWins() + 1);
        });
        teamList.stream().filter(team -> !winnerId.equalsIgnoreCase(String.valueOf(team.getId()))).forEach(team -> {
            team.setMatches(team.getMatches() + 1);
            team.setLoss(team.getLoss() + 1);
        });
        teamRepository.saveAll(teamList);
        return teamList;
    }

    public void setPlayerAndResultAmount(Player player, Result result, Map<String, PlayerPointsSo> playerPointsSoMap, Long winnerCount, Long contestNetAmount) {
        // we first get a Stream of the entries in the map using entrySet().stream(). We then use the sorted() method to sort the entries based
        // on the playerPoints value of the PlayerPointsSo object. Finally, we use the collect() method to collect the sorted entries into a new
        // LinkedHashMap, which preserves the order of insertion.
        Map<String, PlayerPointsSo> playerPointsSoSortedMap = playerPointsSoMap.entrySet().stream()
                .limit(winnerCount)
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(PlayerPointsSo::getPoints).reversed()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // If there are duplicate keys, keep the old value
                        LinkedHashMap::new
                ));
        player.setTotalPoints(player.getTotalPoints() + playerPointsSoSortedMap.get(String.valueOf(player.getId())).getPoints());
        if (playerPointsSoSortedMap.entrySet().iterator().next().getKey().equalsIgnoreCase(String.valueOf(player.getId()))) {
            player.setWins(player.getWins() + 1);
        }
        List<String> winAmountList = getWinAmount(winnerCount, contestNetAmount);
        List<String> playerPointsSoSortedList = new ArrayList<>(playerPointsSoMap.keySet());
        int index = playerPointsSoSortedList.indexOf(String.valueOf(player.getId()));
        player.setNetAmount(player.getNetAmount() + winAmountList.get(index));
        result.setAmountEarned(winAmountList.get(index));
    }

    public List<String> getWinAmount(Long winnerCount, Long contestNetAmount) {
        List<String> winAmount = new ArrayList<>();
        if (winnerCount == 1) {
            winAmount.add(String.valueOf(contestNetAmount));
        } else if (winnerCount == 2) {
            winAmount.add(String.valueOf(0.7 * contestNetAmount));
            winAmount.add(String.valueOf(0.3 * contestNetAmount));
        } else if (winnerCount == 3) {
            winAmount.add(String.valueOf(0.5 * contestNetAmount));
            winAmount.add(String.valueOf(0.3 * contestNetAmount));
            winAmount.add(String.valueOf(0.2 * contestNetAmount));
        } else if (winnerCount == 4) {
            winAmount.add(String.valueOf(0.4 * contestNetAmount));
            winAmount.add(String.valueOf(0.25 * contestNetAmount));
            winAmount.add(String.valueOf(0.2 * contestNetAmount));
            winAmount.add(String.valueOf(0.15 * contestNetAmount));
        } else if (winnerCount == 5) {
            winAmount.add(String.valueOf(0.47 * contestNetAmount));
            winAmount.add(String.valueOf(0.23 * contestNetAmount));
            winAmount.add(String.valueOf(0.15 * contestNetAmount));
            winAmount.add(String.valueOf(0.11 * contestNetAmount));
            winAmount.add(String.valueOf(0.11 * contestNetAmount));
        }
        winAmount.sort(Collections.reverseOrder());
        return winAmount;
    }

    public Long getContestNetAmount(Long particpantCount) {
        return 90 * (particpantCount * participationFee) / 100;
    }

    public Long getWinnerCount(Long participationCount) {
        return participationCount % 4;
    }
}