package com.me2.rest.common.mapper;

import com.me2.entity.User;
import com.me2.service.mapper.EntityMapper;
import com.me2.rest.common.vm.UserEntityVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserVMMapper extends EntityMapper<UserEntityVM, User> {
}