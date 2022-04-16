package com.project.unitech.controller;

import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.dto.response.ApiResponseDto;
import com.project.unitech.dto.response.RegisterByPinResponseDto;
import com.project.unitech.service.AccountService;
import com.project.unitech.service.RegistrationService;
import com.project.unitech.util.PinValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/registration")
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/pin")
    public ResponseEntity<ApiResponseDto<RegisterByPinResponseDto>> registerByPin(@RequestBody RegisterByPinRequestDto registerByPinRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

        PinValidator.validate(registerByPinRequestDto.getPin());

        RegisterByPinResponseDto registerByPinResponseDto = registrationService.registerByPin(registerByPinRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDto.<RegisterByPinResponseDto>builder()
                .data(registerByPinResponseDto)
                .message("success")
                .build());
    }

}
