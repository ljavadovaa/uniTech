package com.project.unitech.service;

import com.project.unitech.dto.ReceiverAccountDto;
import com.project.unitech.dto.SenderAccountDto;
import com.project.unitech.dto.request.TransferRequestDto;
import com.project.unitech.dto.response.TransferResponseDto;

public interface TransferService {
    TransferResponseDto makeTransfer(TransferRequestDto<SenderAccountDto, ReceiverAccountDto> transferDto);
}
