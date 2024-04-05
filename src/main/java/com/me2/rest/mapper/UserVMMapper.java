package com.me2.rest.mapper;

import com.me2.entity.UserEntity;
import com.me2.service.mapper.EntityMapper;
import com.me2.rest.vm.UserEntityVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserVMMapper extends EntityMapper<UserEntityVM, UserEntity> {
}