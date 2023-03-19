package com.fantasy.contestapi.service.impl;

import com.fantasy.contestapi.constant.ErrorMessages;
import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.mapper.AmendContestSoToContestMapper;
import com.fantasy.contestapi.mapper.ContestToContestPerformanceSoMapper;
import com.fantasy.contestapi.repository.ContestRepository;
import com.fantasy.contestapi.schemaobject.AmendContestSo;
import com.fantasy.contestapi.schemaobject.ContestPerformanceSo;
import com.fantasy.contestapi.service.ContestService;
import com.fantasy.contestapi.validation.ContestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;

    @Override
    public List<Contest> getContestDetails(List<Long> ids) {
        return contestRepository.findAllByIdIn(ids);
    }

    @Override
    public void updateContestDetails(List<AmendContestSo> amendContestSoList) {
        if (amendContestSoList.stream().anyMatch(amendContestSo -> ObjectUtils.isNotEmpty(amendContestSo.getId()))) {
            throw new ContestException(ErrorMessages.ERR_CONTEST_ID_INVALID.getCode(), ErrorMessages.ERR_CONTEST_ID_INVALID.getMessage());
        }
        List<Contest> contestList = contestRepository.findAllByIdIn(amendContestSoList.stream().map(AmendContestSo::getId).collect(Collectors.toList()));
        List<Contest> updatedContestList = new ArrayList<>();
        contestList.forEach(contest -> updatedContestList.add(
                AmendContestSoToContestMapper.map(
                        amendContestSoList.stream().
                                filter(amendContestSo -> amendContestSo.getId().equals(contest.getId())).
                                findFirst()
                                .get())));
        contestRepository.saveAll(updatedContestList);
    }

    @Override
    public List<ContestPerformanceSo> fetchContestWinnerDetails() {
        List<Contest> contestList = contestRepository.findAll();
        List<ContestPerformanceSo> contestPerformanceSoList = new ArrayList<>();
        contestList.forEach(contest -> contestPerformanceSoList.add(ContestToContestPerformanceSoMapper.map(contest)));
        return contestPerformanceSoList;
    }
}
