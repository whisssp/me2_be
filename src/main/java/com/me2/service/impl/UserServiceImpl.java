package com.me2.service.impl;

import com.me2.entity.UserEntity;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.global.enums.EnumUserRole;
import com.me2.repository.UserRepository;
import com.me2.service.UserService;
import com.me2.service.dto.UserDTO;
import com.me2.rest.mapper.UserVMMapper;
import com.me2.rest.vm.UserEntityVM;
import com.me2.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserVMMapper userVMMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserVMMapper userVMMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userVMMapper = userVMMapper;
    }

    @Override
    public UserEntityVM save(UserDTO userDTO, EnumUserRole role) {
        log.debug("Request register new account: {} - role: {}", userDTO, role != null ? role : EnumUserRole.USER);
        UserEntity newUser = userMapper.toEntity(userDTO);
        newUser.setRole(role != null ? role : EnumUserRole.USER);
        return userVMMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public UserEntityVM update(UserDTO info) {
        UserEntity newUser = userMapper.toEntity(info);
        return userVMMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserEntityVM getOneUserById(Long id) {
        return userVMMapper.toDto(userRepository.findById(id).orElseThrow(() -> new ErrorHandler(EnumError.USER_NOT_FOUND)));
    }

    @Override
    public Page<UserEntityVM> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userVMMapper::toDto);
    }
}