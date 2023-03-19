package com.fantasy.contestapi.service;

import com.fantasy.contestapi.schemaobject.PerformanceResultSo;

import java.util.List;

public interface ResultService {

    List<PerformanceResultSo> getPerformanceResults(List<Long> playerIds);
}
