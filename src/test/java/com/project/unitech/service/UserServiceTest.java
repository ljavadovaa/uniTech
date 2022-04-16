package com.project.unitech.service;

import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.entity.User;
import com.project.unitech.exception.PinNotUniqueException;
import com.project.unitech.exception.UserNotFoundException;
import com.project.unitech.repository.UserRepository;
import com.project.unitech.service.impl.UserServiceImpl;
import com.project.unitech.tool.ValidationTool;
import com.project.unitech.util.PasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private ValidationTool validationTool;

    @Mock
    private PasswordUtil passwordUtil;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void provideUserByPin() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        RegisterByPinRequestDto registerByPinRequestDto = RegisterByPinRequestDto.builder()
                .pin("AAA1111")
                .name("name")
                .surname("surname")
                .passcode("111111")
                .build();

        Assertions.assertDoesNotThrow(() -> userService.provideUserByPin(registerByPinRequestDto, user));

    }

    @Test
    void findUserById() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(() -> userService.findUserById(String.valueOf(1L)));

    }

    @Test
    void findUserByPin() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        Mockito.when(userRepository.findUserByPin(Mockito.anyString())).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(() -> userService.findUserByPin("pin"));

    }

    @Test
    void setUserPassCode() throws NoSuchAlgorithmException, InvalidKeySpecException {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        Mockito.when(passwordUtil.hash(Mockito.anyString(), Mockito.any())).thenReturn("pass");

        Assertions.assertDoesNotThrow(() -> userService.setUserPassCode(String.valueOf(1L), "1111111"));

    }

    @Test
    void setUserPassCode_userNotFound() {

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.setUserPassCode(String.valueOf(1L), "1111111"));

    }

    @Test
    void register() {

        RegisterByPinRequestDto registerByPinRequestDto = RegisterByPinRequestDto.builder()
                .pin("AAA1111")
                .name("name")
                .surname("surname")
                .passcode("111111")
                .build();

        Mockito.when(validationTool.isPinUnique(Mockito.anyString())).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> userService.register(registerByPinRequestDto));

    }

    @Test
    void register_pinNotUnique() {

        RegisterByPinRequestDto registerByPinRequestDto = RegisterByPinRequestDto.builder()
                .pin("AAA1111")
                .name("name")
                .surname("surname")
                .passcode("111111")
                .build();

        Mockito.when(validationTool.isPinUnique(Mockito.anyString())).thenReturn(false);

        Assertions.assertThrows(PinNotUniqueException.class, () -> userService.register(registerByPinRequestDto));

    }

}
