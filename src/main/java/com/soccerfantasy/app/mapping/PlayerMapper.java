package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.model.response.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

	@Mapping(source = "id", target = "playerId")
	@Mapping(source = "team.id", target = "teamId")
	Player playerEntityToPlayer(PlayerEntity playerEntity);
}
