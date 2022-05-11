package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;

import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.model.response.TeamResponseModel;

@Mapper(componentModel = "spring")
public interface TransferMapper {

	TeamResponseModel teamEntityToTeamResponseModel(TeamEntity teamEntity);
}
