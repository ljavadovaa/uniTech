package com.project.unitech.service.impl;

import com.project.unitech.dto.UserDto;
import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.entity.User;
import com.project.unitech.exception.PinNotUniqueException;
import com.project.unitech.exception.UserNotFoundException;
import com.project.unitech.repository.UserRepository;
import com.project.unitech.service.UserService;
import com.project.unitech.tool.ValidationTool;
import com.project.unitech.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ValidationTool validationTool;
    private final PasswordUtil passwordUtil;
    private final UserRepository userRepository;

    @Override
    public UserDto provideUserByPin(RegisterByPinRequestDto registerByPinRequestDto, User user) {

        return UserDto.builder()
                .user(user)
                .pin(registerByPinRequestDto.getPin())
                .build();
    }

    @Override
    public Optional<User> findUserById(String userId) {
        return userRepository.findById(Long.valueOf(userId));
    }

    @Override
    public void setUserPassCode(String userId, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        Optional<User> optionalUser = findUserById(userId);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            String hashedPassword = "";

            hashedPassword = passwordUtil.hash(password, null);
            user.setPasscode(hashedPassword);

            userRepository.save(user);

        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public Optional<User> findUserByPin(String pin) {
        return userRepository.findUserByPin(pin);
    }

    public User register(RegisterByPinRequestDto registerByPinRequestDto) {

        if (!validationTool.isPinUnique(registerByPinRequestDto.getPin())) {
            throw new PinNotUniqueException();
        }

        User user = User.builder()
                .pin(registerByPinRequestDto.getPin())
                .name(registerByPinRequestDto.getName())
                .surname(registerByPinRequestDto.getSurname())
                .passcode(registerByPinRequestDto.getPasscode())
                .status(0)
                .build();

        userRepository.save(user);

        return user;

    }

}
