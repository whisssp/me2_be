package com.me2.service.impl;

import com.me2.controller.mapper.UserEntityVMMapper;
import com.me2.entity.UserEntity;
import com.me2.repository.UserRepository;
import com.me2.service.UserService;
import com.me2.service.dto.UserDTO;
import com.me2.controller.vm.UserEntityVM;
import com.me2.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final UserEntityVMMapper userEntityVMMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserEntityVMMapper userEntityVMMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userEntityVMMapper = userEntityVMMapper;
    }

    @Override
    public UserEntityVM create(UserDTO userDTO) {
        log.debug("Request register new account: {}", userDTO);
        UserEntity newUser = userMapper.toEntity(userDTO);
        return userEntityVMMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public UserEntityVM update(UserDTO info) {
        UserEntity newUser = userMapper.toEntity(info);
        return userEntityVMMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserEntityVM getOneUserById(Long id) {
        return null;
    }

    @Override
    public Page<UserEntityVM> getAllUsers() {
        return null;
    }
}