package com.project.unitech.service;

import com.project.unitech.dto.UserDto;
import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.entity.User;
import com.project.unitech.repository.AccountRepository;
import com.project.unitech.repository.RegistrationRepository;
import com.project.unitech.service.impl.RegistrationServiceImpl;
import com.project.unitech.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    void registerByPin() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        UserDto userDto = UserDto.builder()
                .user(user)
                .build();

        RegisterByPinRequestDto registerByPinRequestDto = RegisterByPinRequestDto.builder()
                .pin("AAA1111")
                .name("name")
                .surname("surname")
                .passcode("111111")
                .build();

        Mockito.when(userService.register(Mockito.any())).thenReturn(user);

        Mockito.when(userService.provideUserByPin(Mockito.any(), Mockito.any())).thenReturn(userDto);

        Assertions.assertDoesNotThrow(() -> registrationService.registerByPin(registerByPinRequestDto));

    }
}
