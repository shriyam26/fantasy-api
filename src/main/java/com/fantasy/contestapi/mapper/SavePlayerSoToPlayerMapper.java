package com.fantasy.contestapi.mapper;

import com.fantasy.contestapi.entity.Player;
import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SavePlayerSoToPlayerMapper {

    public static Player map(SavePlayerSo savePlayerSo) {
        Player player = new Player();
        BeanUtils.copyProperties(savePlayerSo, player);
        return player;
    }
}
