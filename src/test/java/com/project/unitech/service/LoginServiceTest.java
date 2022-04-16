package com.project.unitech.service;

import com.project.unitech.dto.request.LoginByPinPassRequestDto;
import com.project.unitech.dto.request.LoginRequestWrapper;
import com.project.unitech.entity.User;
import com.project.unitech.entity.UserRegistration;
import com.project.unitech.enums.RegistrationStatus;
import com.project.unitech.exception.InvalidCredentialsException;
import com.project.unitech.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void checkUserCredentials() {

        UserRegistration userRegistration = UserRegistration.builder()
                .user(User.builder().pin("AAA1111").build())
                .status(RegistrationStatus.ACTIVE)
                .build();

        List<UserRegistration> userRegistrationList = Mockito.spy(ArrayList.class);
        userRegistrationList.add(userRegistration);

        User user = User.builder()
                .pin("AAA1111")
                .registrations(userRegistrationList)
                .build();

        LoginByPinPassRequestDto loginByPinPassRequestDto = new LoginByPinPassRequestDto();
        loginByPinPassRequestDto.setPin("AAA1111");
        loginByPinPassRequestDto.setPasscode("111111");

        LoginRequestWrapper<LoginByPinPassRequestDto> loginRequestWrapper = new LoginRequestWrapper<>();
        loginRequestWrapper.setData(loginByPinPassRequestDto);

        Mockito.when(userService.findUserByPin(Mockito.anyString())).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(() -> loginService.checkUserCredentials(loginRequestWrapper));

    }

    @Test
    void checkUserCredentials_notRegistered() {

        List<UserRegistration> userRegistrationList = Mockito.spy(ArrayList.class);

        User user = User.builder()
                .pin("AAA1111")
                .registrations(userRegistrationList)
                .build();

        LoginByPinPassRequestDto loginByPinPassRequestDto = new LoginByPinPassRequestDto();
        loginByPinPassRequestDto.setPin("AAA1111");
        loginByPinPassRequestDto.setPasscode("111111");

        LoginRequestWrapper<LoginByPinPassRequestDto> loginRequestWrapper = new LoginRequestWrapper<>();
        loginRequestWrapper.setData(loginByPinPassRequestDto);

        Mockito.when(userService.findUserByPin(Mockito.anyString())).thenReturn(Optional.of(user));

        Assertions.assertThrows(InvalidCredentialsException.class, () -> loginService.checkUserCredentials(loginRequestWrapper));

    }

    @Test
    void doLogin() {

        UserRegistration userRegistration = UserRegistration.builder()
                .user(User.builder().pin("AAA1111").build())
                .status(RegistrationStatus.ACTIVE)
                .build();

        List<UserRegistration> userRegistrationList = Mockito.spy(ArrayList.class);
        userRegistrationList.add(userRegistration);

        User user = User.builder()
                .pin("AAA1111")
                .registrations(userRegistrationList)
                .build();

        LoginByPinPassRequestDto loginByPinPassRequestDto = new LoginByPinPassRequestDto();
        loginByPinPassRequestDto.setPin("AAA1111");
        loginByPinPassRequestDto.setPasscode("111111");

        LoginRequestWrapper<LoginByPinPassRequestDto> loginRequestWrapper = new LoginRequestWrapper<>();
        loginRequestWrapper.setData(loginByPinPassRequestDto);

        Mockito.when(userService.findUserByPin(Mockito.anyString())).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(() -> loginService.doLogin(loginRequestWrapper));

    }


}
