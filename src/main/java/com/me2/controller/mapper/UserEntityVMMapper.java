package com.me2.controller.mapper;

import com.me2.controller.vm.UserEntityVM;
import com.me2.entity.UserEntity;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserEntityVMMapper extends EntityMapper<UserEntityVM, UserEntity> {
}