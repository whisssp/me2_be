package com.me2.service.impl;

import com.me2.entity.User;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumError;
import com.me2.global.enums.EnumUserAccountStatus;

import com.me2.global.enums.EnumUserRole;
import com.me2.repository.UserRepository;
import com.me2.global.response.Paginate;
import com.me2.service.UserService;
import com.me2.service.dto.UserDTO;
import com.me2.rest.common.mapper.UserVMMapper;
import com.me2.rest.common.vm.UserEntityVM;
import com.me2.service.dto.UserUpdateDTO;
import com.me2.service.mapper.UserMapper;
import com.me2.service.mapper.UserUpdateMapper;
import com.me2.util.PageUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        User newUser = userMapper.toEntity(userDTO);
        newUser.setRole(role != null ? role : EnumUserRole.USER);
        newUser.setStatus(EnumUserAccountStatus.ACTIVATED);
        return userVMMapper.toDto(saving(newUser));
    }

    @Override
    public Optional<User> saveForUser(UserDTO userDTO) {
        User newUser = userMapper.toEntity(userDTO);
        newUser.setRole(EnumUserRole.USER);
        newUser.setStatus(EnumUserAccountStatus.ACTIVATED);
        Optional<User> optionalUser = Optional.of(saving(newUser));
        updateAudit(optionalUser.orElse(null));
        return optionalUser;
    }

    @Override
    public void saveForResetPassword(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (user == null) throw new CustomException(EnumError.USER_NOT_FOUND);
        return user;
    }

    @Override
    public UserEntityVM update(UserUpdateDTO userDto) {
        log.debug("Request to update user with info: {}", userDto.toString());
        User user = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new CustomException(EnumError.USER_NOT_FOUND));
        userUpdateMapper.partialUpdate(user, userDto);
        return userVMMapper.toDto(saving(user));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(EnumError.USER_NOT_FOUND));
        user.setStatus(EnumUserAccountStatus.DELETED);
        saving(user);
    }

    @Override
    public UserEntityVM getOneUserById(Long id) {
        log.debug("Request to get user by id: {}", id);
        return userVMMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new CustomException(EnumError.USER_NOT_FOUND)));
    }

    @Override
    public Paginate<UserEntityVM> getAllUsers(Pageable pageable) {
        log.debug("Request to get all users");
        return PageUtil
                .toPaginateResponse(userRepository.findAll(pageable)
                                    .map(userVMMapper::toDto));
    }

    @Async
    protected void updateAudit(User user) {
        if (user == null) return;
        user.setCreatedBy(user.getId()+"");
        user.setLastModifiedBy(user.getId()+"");
        user.setLastModifiedDate(user.getLastModifiedDate());
    }

    private User saving(User user) {
        return userRepository.save(user);
    }
}