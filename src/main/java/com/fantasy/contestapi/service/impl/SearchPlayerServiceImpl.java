package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.mapper.PlayerToSearchPlayerResponseSoMapper;
import com.fantasy.contestapi.repository.PlayerRepository;
import com.fantasy.contestapi.schemaobject.SearchPlayerResponseSo;
import com.fantasy.contestapi.service.SearchPlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlayerServiceImpl implements SearchPlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<SearchPlayerResponseSo> getPlayerList() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream().
                map(PlayerToSearchPlayerResponseSoMapper::map)
                .collect(Collectors.toList());
    }
}
