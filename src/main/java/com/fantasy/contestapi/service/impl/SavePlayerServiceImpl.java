package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.mapper.SavePlayerSoToPlayerMapper;
import com.fantasy.contestapi.repository.PlayerRepository;
import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import com.fantasy.contestapi.service.SavePlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SavePlayerServiceImpl implements SavePlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public void savePlayer(List<SavePlayerSo> savePlayerSoList) {
        if (savePlayerSoList.stream().anyMatch(savePlayerSo -> StringUtils.isBlank(savePlayerSo.getPlayerName()))) {
            //throw error here
            log.error("Please send valid team name");
            return;
        }
        List<Player> playerList = new ArrayList<>();
        savePlayerSoList.forEach(savePlayerSo -> playerList.add(SavePlayerSoToPlayerMapper.map(savePlayerSo)));
        playerRepository.saveAll(playerList);
    }
}
