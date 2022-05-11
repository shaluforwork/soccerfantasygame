package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.model.response.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

	Player playerEntityToPlayer(PlayerEntity playerEntity);
}
