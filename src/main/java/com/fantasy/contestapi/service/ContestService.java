package com.fantasy.contestapi.service;

import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.schemaobject.AmendContestSo;
import com.fantasy.contestapi.schemaobject.ContestPerformanceSo;

import java.util.List;

public interface ContestService {

    List<Contest> getContestDetails(List<Long> ids);

    void updateContestDetails(List<AmendContestSo> amendContestSoList);

    List<ContestPerformanceSo> fetchContestWinnerDetails();
}
