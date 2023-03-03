package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.schemaobject.PlayerPointsSo;
import com.fantasy.contestapi.service.SaveContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveContestServiceImpl implements SaveContestService {

    @Override
    public void saveContainer(PlayerPointsSo playerPointsSo) {
        log.info("Method reached.");
    }
}