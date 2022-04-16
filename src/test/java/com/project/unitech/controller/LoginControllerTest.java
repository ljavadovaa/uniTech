package com.project.unitech.controller;

import com.project.unitech.dto.request.LoginByPinPassRequestDto;
import com.project.unitech.dto.response.LoginResponseDto;
import com.project.unitech.dto.response.LoginResponseWrapper;
import com.project.unitech.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void loginByPinPass() {

        LoginByPinPassRequestDto loginByPinPassRequestDto = new LoginByPinPassRequestDto();
        loginByPinPassRequestDto.setPin("AAA1111");
        loginByPinPassRequestDto.setPasscode("111111");

        LoginResponseWrapper<LoginResponseDto> loginResponseWrapper = new LoginResponseWrapper<>();
        loginResponseWrapper.setData(LoginResponseDto.builder()
                .userId("id")
                .build());

        Mockito.when(loginService.doLogin(Mockito.any())).thenReturn(loginResponseWrapper);

        Assertions.assertDoesNotThrow(() -> loginController.loginByPinPass(loginByPinPassRequestDto));

    }

}
