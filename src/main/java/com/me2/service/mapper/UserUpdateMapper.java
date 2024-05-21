package com.me2.service.mapper;

import com.me2.entity.User;
import com.me2.service.dto.UserUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserUpdateMapper extends EntityMapper<UserUpdateDTO, User>{
}