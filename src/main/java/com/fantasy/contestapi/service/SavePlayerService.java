package com.fantasy.contestapi.service;

import com.fantasy.contestapi.schemaobject.SavePlayerSo;

import java.util.List;

public interface SavePlayerService {

    void savePlayer(List<SavePlayerSo> savePlayerSoList);
}
