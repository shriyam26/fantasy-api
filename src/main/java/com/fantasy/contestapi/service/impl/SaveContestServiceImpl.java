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
import com.fantasy.contestapi.validation.ContestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveContestServiceImpl implements SaveContestService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final ContestRepository contestRepository;
    private final ResultRepository resultRepository;
    private final ContestValidator contestValidator;

    @Value("${contest.ipl.participationFee:}")
    private Long participationFee;
    @Value("${contest.ipl.winnerCount1.winPercentageShare:}")
    private Long oneWinnerAmount;
    @Value("${contest.ipl.winnerCount2.winPercentageShare1:}")
    private Long twoWinnerAmount1;
    @Value("${contest.ipl.winnerCount2.winPercentageShare2:}")
    private Long twoWinnerAmount2;
    @Value("${contest.ipl.winnerCount3.winPercentageShare1:}")
    private Long threeWinnerAmount1;
    @Value("${contest.ipl.winnerCount3.winPercentageShare2:}")
    private Long threeWinnerAmount2;
    @Value("${contest.ipl.winnerCount3.winPercentageShare3:}")
    private Long threeWinnerAmount3;
    @Value("${contest.ipl.winnerCount4.winPercentageShare1:}")
    private Long fourWinnerAmount1;
    @Value("${contest.ipl.winnerCount4.winPercentageShare2:}")
    private Long fourWinnerAmount2;
    @Value("${contest.ipl.winnerCount4.winPercentageShare3:}")
    private Long fourWinnerAmount3;
    @Value("${contest.ipl.winnerCount4.winPercentageShare4:}")
    private Long fourWinnerAmount4;
    @Value("${contest.ipl.winnerCount5.winPercentageShare1:}")
    private Long fiveWinnerAmount1;
    @Value("${contest.ipl.winnerCount5.winPercentageShare2:}")
    private Long fiveWinnerAmount2;
    @Value("${contest.ipl.winnerCount5.winPercentageShare3:}")
    private Long fiveWinnerAmount3;
    @Value("${contest.ipl.winnerCount5.winPercentageShare4:}")
    private Long fiveWinnerAmount4;
    @Value("${contest.ipl.winnerCount5.winPercentageShare5:}")
    private Long fiveWinnerAmount5;

    @Override
    public void saveContest(SaveContestSo saveContestSo) {
        contestValidator.validate(saveContestSo);
        List<Team> teamList = updateTeamScore(saveContestSo.getHomeTeamId(), saveContestSo.getAwayTeamId(), saveContestSo.getWinnerId());

        saveContestSo.getPlayerPointsSoList().sort(Comparator.comparing(PlayerPointsSo::getPoints, Comparator.reverseOrder()));
        List<Long> playerIds = saveContestSo.getPlayerPointsSoList().stream().map(PlayerPointsSo::getPlayerId).collect(Collectors.toList());
        List<Player> playerList = playerRepository.findByIdIn(playerIds);
        Map<Long, PlayerPointsSo> playerPointsSoMap = new HashMap<>();
        saveContestSo.getPlayerPointsSoList().forEach(playerPointsSo -> playerPointsSoMap.put(playerPointsSo.getPlayerId(), playerPointsSo));

        Map<Long, Player> playerMap = new HashMap<>();
        playerList.forEach(player -> playerMap.put(player.getId(), player));

        Long participantCount = playerPointsSoMap.values().stream().filter(playerPointsSo -> StringUtils.isNotBlank(playerPointsSo.getPoints())).count();
        Long contestNetAmount = getContestNetAmount(participantCount);
        Long winnerCount = getWinnerCount(participantCount);
        List<Double> winAmountList = getWinAmount(winnerCount, contestNetAmount);
        Map<Long, PlayerPointsSo> playerPointsSoSortedMap = getPlayerPointsSortedMap(playerPointsSoMap, winnerCount);
        log.info("Participant count: {} and contest amount: {}", participantCount, contestNetAmount);

        Contest contest = new Contest();
        contest.setHomeTeam(teamList.stream().filter(team -> Objects.equals(saveContestSo.getHomeTeamId(), team.getId())).findFirst().orElse(null));
        contest.setAwayTeam(teamList.stream().filter(team -> Objects.equals(saveContestSo.getAwayTeamId(), team.getId())).findFirst().orElse(null));
        contest.setMatchTime(saveContestSo.getMatchTime());
        contest.setWinnerCount(String.valueOf(winnerCount));
        contest.setContestValue(String.valueOf(contestNetAmount));
        contestRepository.save(contest);
        teamRepository.saveAll(teamList);

        List<Result> resultList = new ArrayList<>();
        List<Player> playerUpdatedList = new ArrayList<>();
        saveContestSo.getPlayerPointsSoList().forEach(playerPointsSo -> {
            Long playerId = playerPointsSo.getPlayerId();
            Player player = playerMap.get(playerId);
            Result result = new Result();
            result.setContest(contest);
            result.setPlayer(player);
            result.setPoints(playerPointsSo.getPoints());
            setPlayerPoints(playerPointsSoMap, playerId, player);
            setPlayerAndResultAmount(player, result, playerPointsSoSortedMap, winAmountList, playerId);
            resultList.add(result);
            playerUpdatedList.add(player);
        });
        resultRepository.saveAll(resultList);
        playerRepository.saveAll(playerUpdatedList);
        log.info("Data updated successfully for contest: {}", contest.getId());
    }

    public List<Team> updateTeamScore(Long homeTeamId, Long awayTeamId, Long winnerId) {
        //Think no result case and nrr case.
        List<Team> teamList = teamRepository.findByIdIn(Arrays.asList(homeTeamId, awayTeamId));
        if (winnerId > 0) {
            teamList.stream().filter(team -> winnerId.equals(team.getId())).forEach(team -> {
                team.setMatches(team.getMatches() + 1);
                team.setPoints(team.getPoints() + 3);
                team.setWins(team.getWins() + 1);
            });
            teamList.stream().filter(team -> !winnerId.equals(team.getId())).forEach(team -> {
                team.setMatches(team.getMatches() + 1);
                team.setLoss(team.getLoss() + 1);
            });
        } else {
            teamList.forEach(team -> {
                team.setMatches(team.getMatches() + 1);
                team.setPoints(team.getPoints() + 1);
                team.setWins(team.getNoResult() + 1);
            });
        }
        return teamList;
    }

    private void setPlayerPoints(Map<Long, PlayerPointsSo> playerPointsSoMap, Long playerId, Player player) {
        double currentTotalPoints = StringUtils.isNotBlank(player.getTotalPoints()) ? Double.parseDouble(player.getTotalPoints()) : 0.0;
        player.setTotalPoints(String.valueOf(currentTotalPoints + Double.parseDouble(playerPointsSoMap.get(playerId).getPoints())));
        log.info("Points earned by player: {} is: {}", player.getPlayerName(), playerPointsSoMap.get(playerId).getPoints());
    }

    public void setPlayerAndResultAmount(
            Player player,
            Result result,
            Map<Long, PlayerPointsSo> playerPointsSoSortedMap,
            List<Double> winnerAmountList, Long playerId
    ) {
        if (playerPointsSoSortedMap.entrySet().iterator().next().getKey().equals(player.getId())) {
            long wins = StringUtils.isNotBlank(player.getWins()) ? Long.parseLong(player.getWins()) : 0;
            player.setWins(String.valueOf(wins + 1));
        }

        List<Long> playerPointsSoSortedList = new ArrayList<>(playerPointsSoSortedMap.keySet());
        int index = playerPointsSoSortedList.indexOf(playerId);
        long position = (index + 1);
        result.setPosition(position);
        double currentAmount = StringUtils.isNotBlank(player.getNetAmount()) ? Double.parseDouble(player.getNetAmount()) : 0.0;
        if (position > winnerAmountList.size()) {
            player.setNetAmount(String.valueOf(currentAmount));
            result.setAmountEarned("0");
            log.info("Amount earned by player: {} is: {}", player.getPlayerName(), 0);
        } else {
            player.setNetAmount(String.valueOf(currentAmount + winnerAmountList.get(index)));
            result.setAmountEarned(String.valueOf(winnerAmountList.get(index)));
            log.info("Amount earned by player: {} is: {}", player.getPlayerName(), winnerAmountList.get(index));
        }
    }

    private Map<Long, PlayerPointsSo> getPlayerPointsSortedMap(Map<Long, PlayerPointsSo> playerPointsSoMap) {
        // we first get a Stream of the entries in the map using entrySet().stream(). We then use the sorted() method to sort the entries based
        // on the playerPoints value of the PlayerPointsSo object. Finally, we use the collect() method to collect the sorted entries into a new
        // LinkedHashMap, which preserves the order of insertion.
        return playerPointsSoMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(PlayerPointsSo::getPoints).reversed()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // If there are duplicate keys, keep the old value
                        LinkedHashMap::new
                ));
    }

    private List<Double> getWinAmount(Long winnerCount, Long contestNetAmount) {
        List<Double> winAmount = new ArrayList<>();
        if (winnerCount == 1) {
            winAmount.add((((double) oneWinnerAmount * (double) contestNetAmount) / 100));
        } else if (winnerCount == 2) {
            winAmount.add((((double) twoWinnerAmount1 * (double) contestNetAmount) / 100));
            winAmount.add((((double) twoWinnerAmount2 * (double) contestNetAmount) / 100));
        } else if (winnerCount == 3) {
            winAmount.add((((double) threeWinnerAmount1 * (double) contestNetAmount) / 100));
            winAmount.add((((double) threeWinnerAmount2 * (double) contestNetAmount) / 100));
            winAmount.add((((double) threeWinnerAmount3 * (double) contestNetAmount) / 100));
        } else if (winnerCount == 4) {
            winAmount.add((((double) fourWinnerAmount1 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fourWinnerAmount2 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fourWinnerAmount3 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fourWinnerAmount4 * (double) contestNetAmount) / 100));
        } else if (winnerCount == 5) {
            winAmount.add((((double) fiveWinnerAmount1 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fiveWinnerAmount2 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fiveWinnerAmount3 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fiveWinnerAmount4 * (double) contestNetAmount) / 100));
            winAmount.add((((double) fiveWinnerAmount5 * (double) contestNetAmount) / 100));
        }
        winAmount.sort(Collections.reverseOrder());
        return winAmount;
    }

    private Long getContestNetAmount(Long participantCount) {
        return 90 * (participantCount * participationFee) / 100;
    }

    private Long getWinnerCount(Long participantCount) {
        return participantCount / 2;
    }
}