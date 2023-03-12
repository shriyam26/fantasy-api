package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.schemaobject.SearchPlayerResponseSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerToSearchPlayerResponseSoMapper {

    public static SearchPlayerResponseSo map(Player player) {
        SearchPlayerResponseSo responseSo = new SearchPlayerResponseSo();
        BeanUtils.copyProperties(player, responseSo);
        return responseSo;
    }
}
