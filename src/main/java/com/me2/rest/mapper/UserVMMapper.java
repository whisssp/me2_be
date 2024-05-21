package com.me2.rest.mapper;

import com.me2.entity.User;
import com.me2.rest.admin.mapper.AddressAdminVMMapper;
import com.me2.rest.admin.mapper.CartAdminVMMapper;
import com.me2.rest.admin.mapper.OrderAdminVMMapper;
import com.me2.service.mapper.EntityMapper;
import com.me2.rest.vm.UserEntityVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserVMMapper extends EntityMapper<UserEntityVM, User> {
}