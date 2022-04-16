package com.project.unitech.service.impl;

import com.project.unitech.dto.request.LoginByPinPassRequestDto;
import com.project.unitech.dto.request.LoginRequestWrapper;
import com.project.unitech.dto.response.LoginResponseDto;
import com.project.unitech.dto.response.LoginResponseWrapper;
import com.project.unitech.entity.User;
import com.project.unitech.entity.UserRegistration;
import com.project.unitech.enums.RegistrationStatus;
import com.project.unitech.exception.InvalidCredentialsException;
import com.project.unitech.service.LoginService;
import com.project.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    public User checkUserCredentials(LoginRequestWrapper<LoginByPinPassRequestDto> loginRequestWrapper) {

        LoginByPinPassRequestDto loginByPinPassRequestDto = loginRequestWrapper.getData();
        String pin = loginByPinPassRequestDto.getPin();

        User user = userService.findUserByPin(pin).orElseThrow(InvalidCredentialsException::new);

        List<UserRegistration> userRegistrationList = user.getRegistrations()
                .stream()
                .filter(r-> r.getUser().getPin().equals(pin)
                        && r.getStatus() == RegistrationStatus.ACTIVE)
                .collect(Collectors.toList());

        if (userRegistrationList.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        UserRegistration userRegistration = userRegistrationList.get(0);

        return userRegistration.getUser();

    }

    @Override
    public LoginResponseWrapper doLogin(LoginRequestWrapper loginRequestWrapper) {

        User user = checkUserCredentials(loginRequestWrapper);

        LoginResponseDto loginResponseDTO = LoginResponseDto.builder()
                .userId(String.valueOf(user.getId()))
                .build();

        LoginResponseWrapper<LoginResponseDto> loginResponseWrapper = new LoginResponseWrapper<>();
        loginResponseWrapper.setData(loginResponseDTO);

        return loginResponseWrapper;
    }
}
