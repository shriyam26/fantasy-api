package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.constant.ErrorMessages;
import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.mapper.AmendPlayerSoToPlayerMapper;
import com.fantasy.contestapi.mapper.SavePlayerSoToPlayerMapper;
import com.fantasy.contestapi.repository.PlayerRepository;
import com.fantasy.contestapi.schemaobject.AmendPlayerSo;
import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import com.fantasy.contestapi.service.SavePlayerService;
import com.fantasy.contestapi.validation.ContestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SavePlayerServiceImpl implements SavePlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public void savePlayer(List<SavePlayerSo> savePlayerSoList) {
        if (savePlayerSoList.stream().anyMatch(savePlayerSo -> StringUtils.isBlank(savePlayerSo.getPlayerName()))) {
            throw new ContestException(ErrorMessages.ERR_PLAYER_INVALID.getCode(), ErrorMessages.ERR_PLAYER_INVALID.getMessage());
        }
        List<Player> playerList = new ArrayList<>();
        savePlayerSoList.forEach(savePlayerSo -> playerList.add(SavePlayerSoToPlayerMapper.map(savePlayerSo)));
        playerRepository.saveAll(playerList);
    }

    @Override
    public void updatePlayer(List<AmendPlayerSo> amendPlayerSoList) {
        if (amendPlayerSoList.stream().anyMatch(savePlayerSo -> StringUtils.isBlank(savePlayerSo.getPlayerName()))) {
            throw new ContestException(ErrorMessages.ERR_PLAYER_INVALID.getCode(), ErrorMessages.ERR_PLAYER_INVALID.getMessage());
        }
        List<Player> playerList = playerRepository.findByIdIn(amendPlayerSoList.stream().map(AmendPlayerSo::getId).collect(Collectors.toList()));
        List<Player> updatedPlayerList = new ArrayList<>();
        playerList.forEach(player -> updatedPlayerList.add(
                AmendPlayerSoToPlayerMapper.map(
                        amendPlayerSoList.stream().
                                filter(amendPlayerSo -> amendPlayerSo.getId().equals(player.getId()))
                                .findFirst()
                                .get())));
        playerRepository.saveAll(updatedPlayerList);
    }
}
