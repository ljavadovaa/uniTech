package com.project.unitech.service.impl;

import com.project.unitech.dto.UserDto;
import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.dto.response.RegisterByPinResponseDto;
import com.project.unitech.entity.Transfer;
import com.project.unitech.entity.User;
import com.project.unitech.entity.UserAccount;
import com.project.unitech.entity.UserRegistration;
import com.project.unitech.enums.RegistrationStatus;
import com.project.unitech.repository.AccountRepository;
import com.project.unitech.repository.RegistrationRepository;
import com.project.unitech.repository.TransferRepository;
import com.project.unitech.service.RegistrationService;
import com.project.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;
    private final RegistrationRepository registrationRepository;
    private final AccountRepository accountRepository;

    @Override
    public RegisterByPinResponseDto registerByPin(RegisterByPinRequestDto registerByPinRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

        User registeredUser = userService.register(registerByPinRequestDto);

        UserRegistration userRegistration = UserRegistration.builder()
                .status(RegistrationStatus.ACTIVE)
                .user(registeredUser)
                .build();

        registrationRepository.save(userRegistration);

        UserAccount userAccount = UserAccount.builder()
                .status(RegistrationStatus.ACTIVE)
                .user(registeredUser)
                .build();

        accountRepository.save(userAccount);

        UserDto userDto = userService.provideUserByPin(registerByPinRequestDto, registeredUser);

        String userId = String.valueOf(registeredUser.getId());
        String passcode = registeredUser.getPasscode();

        userService.setUserPassCode(userId, passcode);

        return RegisterByPinResponseDto.builder()
                .userId(String.valueOf(userDto.getUser().getId()))
                .build();

    }
}
