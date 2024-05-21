package com.me2.rest.admin.mapper;

import com.me2.entity.Order;
import com.me2.rest.admin.vm.OrderAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderAdminVMMapper extends EntityMapper<OrderAdminVM, Order> {
}