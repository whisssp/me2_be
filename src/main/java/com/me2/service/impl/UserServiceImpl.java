package com.me2.service.impl;

import com.me2.entity.UserEntity;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.global.enums.EnumUserAccountStatus;
import com.me2.global.enums.EnumUserRole;
import com.me2.repository.UserRepository;
import com.me2.rest.response.Paginate;
import com.me2.service.UserService;
import com.me2.service.dto.UserDTO;
import com.me2.rest.mapper.UserVMMapper;
import com.me2.rest.vm.UserEntityVM;
import com.me2.service.dto.UserUpdateDTO;
import com.me2.service.mapper.UserMapper;
import com.me2.service.mapper.UserUpdateMapper;
import com.me2.util.PageUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserVMMapper userVMMapper;

    private final UserUpdateMapper userUpdateMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           UserVMMapper userVMMapper,
                           UserUpdateMapper userUpdateMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userVMMapper = userVMMapper;
        this.userUpdateMapper = userUpdateMapper;
    }

    @Override
    public UserEntityVM save(UserDTO userDTO, EnumUserRole role) {
        log.debug("Request register new account: {} - role: {}", userDTO, role != null ? role : EnumUserRole.USER);
        UserEntity newUser = userMapper.toEntity(userDTO);
        newUser.setRole(role != null ? role : EnumUserRole.USER);
        newUser.setStatus(EnumUserAccountStatus.ACTIVATED);
        return userVMMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public UserEntityVM update(UserUpdateDTO userDto) {
        log.debug("Request to update user with info: {}", userDto.toString());
        UserEntity user = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ErrorHandler(EnumError.USER_NOT_FOUND));
        userUpdateMapper.partialUpdate(user, userDto);
        return userVMMapper.toDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete user by id: {}", id);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.USER_NOT_FOUND));
        user.setStatus(EnumUserAccountStatus.DELETED);
        userRepository.save(user);
    }

    @Override
    public UserEntityVM getOneUserById(Long id) {
        log.debug("Request to get user by id: {}", id);
        return userVMMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.USER_NOT_FOUND)));
    }

    @Override
    public Paginate<UserEntityVM> getAllUsers(Pageable pageable) {
        log.debug("Request to get all users");
        return new PageUtil<UserEntityVM>()
                .toPaginateResponse(userRepository.findAll(pageable)
                                    .map(userVMMapper::toDto));
    }
}