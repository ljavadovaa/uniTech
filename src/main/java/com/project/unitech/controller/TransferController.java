package com.project.unitech.controller;

import com.project.unitech.dto.ReceiverAccountDto;
import com.project.unitech.dto.SenderAccountDto;
import com.project.unitech.dto.request.TransferRequestDto;
import com.project.unitech.dto.response.ApiResponseDto;
import com.project.unitech.dto.response.TransferResponseDto;
import com.project.unitech.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/account-to-account")
    public ApiResponseDto<TransferResponseDto> makeAccountToAccountTransfer(
            @RequestBody @Valid TransferRequestDto<SenderAccountDto, ReceiverAccountDto> accountToAccountDTO) {

        TransferResponseDto transferResponseDto = transferService.makeTransfer(accountToAccountDTO);

        return ApiResponseDto.<TransferResponseDto>builder()
                .data(transferResponseDto)
                .message("success")
                .build();
    }

}
