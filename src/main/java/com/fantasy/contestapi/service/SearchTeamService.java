package com.fantasy.contestapi.service;

import com.fantasy.contestapi.schemaobject.SearchTeamResponseSo;

import java.util.List;

public interface SearchTeamService {

    List<SearchTeamResponseSo> getTeamList();
}
