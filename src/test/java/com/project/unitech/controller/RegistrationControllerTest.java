package com.project.unitech.controller;

import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.dto.response.RegisterByPinResponseDto;
import com.project.unitech.exception.InvalidPinException;
import com.project.unitech.exception.PinNotUniqueException;
import com.project.unitech.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private RegistrationServiceImpl registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    void registerByPin_success() throws NoSuchAlgorithmException, InvalidKeySpecException {

        RegisterByPinRequestDto registerByPinRequestDto = RegisterByPinRequestDto.builder()
                .pin("AAA1111")
                .name("name")
                .surname("surname")
                .passcode("111111")
                .build();

        RegisterByPinResponseDto registerByPinResponseDto = RegisterByPinResponseDto.builder()
                .userId("userId")
                .build();

        Mockito.when(registrationService.registerByPin(Mockito.any())).thenReturn(registerByPinResponseDto);

        Assertions.assertDoesNotThrow(() -> registrationController.registerByPin(registerByPinRequestDto));

    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void registerByPin_InvalidPin() throws NoSuchAlgorithmException, InvalidKeySpecException {

        RegisterByPinRequestDto registerByPinRequestDto = RegisterByPinRequestDto.builder()
                .pin("1111111")
                .name("name")
                .surname("surname")
                .passcode("111111")
                .build();

        RegisterByPinResponseDto registerByPinResponseDto = RegisterByPinResponseDto.builder()
                .userId("userId")
                .build();

        Mockito.when(registrationService.registerByPin(Mockito.any())).thenReturn(registerByPinResponseDto);

        Assertions.assertThrows(InvalidPinException.class, () -> registrationController.registerByPin(registerByPinRequestDto));

    }

}