package com.soccerfantasy.app.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.UserResponseModel;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "team.id", target = "teamId")
    UserResponseModel userEntityToUserResponseModel(UserEntity user);

    UserEntity userRequestModelToUserEntity(UserRequestModel userRequestModel);
}
