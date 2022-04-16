package com.project.unitech.service;

import com.project.unitech.dto.request.RegisterByPinRequestDto;
import com.project.unitech.dto.response.RegisterByPinResponseDto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface RegistrationService {

    RegisterByPinResponseDto registerByPin(RegisterByPinRequestDto registerByPinRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
