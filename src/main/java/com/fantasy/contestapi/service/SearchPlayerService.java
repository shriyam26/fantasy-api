package com.fantasy.contestapi.service;

import com.fantasy.contestapi.schemaobject.SearchPlayerResponseSo;

import java.util.List;

public interface SearchPlayerService {

    List<SearchPlayerResponseSo> getPlayerList();
}
