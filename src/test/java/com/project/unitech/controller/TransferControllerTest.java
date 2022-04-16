package com.project.unitech.controller;

import com.project.unitech.dto.ReceiverAccountDto;
import com.project.unitech.dto.SenderAccountDto;
import com.project.unitech.dto.request.TransferRequestDto;
import com.project.unitech.dto.response.TransferResponseDto;
import com.project.unitech.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @Test
    void makeAccountToAccountTransfer() {

        TransferResponseDto transferResponseDto = TransferResponseDto.builder()
                .transferId("id")
                .build();

        TransferRequestDto<SenderAccountDto, ReceiverAccountDto> accountToAccountDTO = new TransferRequestDto<>();
        accountToAccountDTO.setAmount(BigDecimal.valueOf(100));

        Mockito.when(transferService.makeTransfer(Mockito.any())).thenReturn(transferResponseDto);

        Assertions.assertDoesNotThrow(() -> transferController.makeAccountToAccountTransfer(accountToAccountDTO));

    }

}
