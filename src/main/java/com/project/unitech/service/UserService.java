package com.project.unitech.service;

import com.project.unitech.dto.UserDto;
import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public interface UserService {

    User register(RegisterByPinRequestDto registerByPinRequestDto);

    UserDto provideUserByPin(RegisterByPinRequestDto registerByPinRequestDto, User user);

    Optional<User> findUserById(String userId);

    void setUserPassCode(String userId, String passcode) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Optional<User> findUserByPin(String pin);
}
