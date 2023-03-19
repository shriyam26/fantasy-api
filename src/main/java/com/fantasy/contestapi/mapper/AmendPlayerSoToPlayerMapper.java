package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.schemaobject.AmendPlayerSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AmendPlayerSoToPlayerMapper {

    public static Player map(AmendPlayerSo amendPlayerSo) {
        Player player = new Player();
        BeanUtils.copyProperties(amendPlayerSo, player);
        return player;
    }
}
