package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.model.response.TeamResponseModel;

@Mapper(componentModel = "spring")
public interface TeamMapper {

	@Mapping(source = "id", target = "teamId")
	TeamResponseModel teamEntityToTeamResponseModel(TeamEntity teamEntity);
}
