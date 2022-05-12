package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soccerfantasy.app.domain.TransferListEntity;
import com.soccerfantasy.app.model.request.TransferListRequestModel;
import com.soccerfantasy.app.model.response.TransferListResponseModel;

@Mapper(componentModel = "spring")
public interface TransferListMapper {

	TransferListEntity transferListRequestModelToTransferListEntity(TransferListRequestModel transferListRequestModel);

	@Mapping(source = "id", target = "transferListId")
	@Mapping(source = "player.id", target = "player.playerId")
	@Mapping(source = "player.team.id", target = "player.teamId")
	TransferListResponseModel transferListEntityToTransferListResponseModel(TransferListEntity transferListEntity);
}
