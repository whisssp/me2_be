package com.me2.rest.admin.mapper;

import com.me2.entity.Address;
import com.me2.rest.admin.vm.AddressAdminVM;
import com.me2.service.dto.admin.AddressAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AddressAdminVMMapper extends EntityMapper<AddressAdminVM, Address> {
}