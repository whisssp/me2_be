package com.me2.service.mapper;

import com.me2.entity.UserEntity;
import com.me2.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, UserEntity> {
}