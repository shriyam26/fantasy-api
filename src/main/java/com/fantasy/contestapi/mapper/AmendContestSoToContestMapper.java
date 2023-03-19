package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.schemaobject.AmendContestSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AmendContestSoToContestMapper {

    public static Contest map(AmendContestSo amendContestSo) {
        Contest contest = new Contest();
        BeanUtils.copyProperties(amendContestSo, contest);
        return contest;
    }
}
