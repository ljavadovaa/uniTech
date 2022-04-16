package com.project.unitech.controller;

import com.project.unitech.dto.request.LoginRequestWrapper;
import com.project.unitech.dto.request.LoginByPinPassRequestDto;
import com.project.unitech.dto.response.ApiResponseDto;
import com.project.unitech.dto.response.LoginResponseDto;
import com.project.unitech.dto.response.LoginResponseWrapper;
import com.project.unitech.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/pin-pass")
    public ApiResponseDto<LoginResponseDto> loginByPinPass(@Valid @RequestBody LoginByPinPassRequestDto loginByPinPassRequestDto) {

        LoginRequestWrapper<LoginByPinPassRequestDto> request = new LoginRequestWrapper<>();
        request.setData(loginByPinPassRequestDto);

        LoginResponseWrapper<LoginResponseDto> loginResponseWrapper = loginService.doLogin(request);

        LoginResponseDto loginResponseDTO = loginResponseWrapper.getData();

        return ApiResponseDto.<LoginResponseDto>builder()
                .data(loginResponseDTO)
                .message("success")
                .build();

    }

}
