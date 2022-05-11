package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;

import com.soccerfantasy.app.domain.TransferListEntity;
import com.soccerfantasy.app.model.request.TransferListRequestModel;
import com.soccerfantasy.app.model.response.TransferListResponseModel;

@Mapper(componentModel = "spring")
public interface TransferListMapper {

	TransferListEntity transferListRequestModelToTransferListEntity(TransferListRequestModel transferListRequestModel);

	TransferListResponseModel transferListEntityToTransferListResponseModel(TransferListEntity transferListEntity);
}
