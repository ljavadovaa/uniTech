package com.project.unitech.service;

import com.project.unitech.dto.ReceiverAccountDto;
import com.project.unitech.dto.SenderAccountDto;
import com.project.unitech.dto.request.TransferRequestDto;
import com.project.unitech.entity.User;
import com.project.unitech.enums.CurrencyEnum;
import com.project.unitech.exception.InvalidTransferException;
import com.project.unitech.repository.TransferRepository;
import com.project.unitech.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferServiceImpl transferService;

    @Test
    void makeTransfer_success() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        TransferRequestDto<SenderAccountDto, ReceiverAccountDto> transferDto = new TransferRequestDto<>();
        transferDto.setAmount(BigDecimal.valueOf(100));
        transferDto.setCurrency(CurrencyEnum.AZN);
        transferDto.setSender(SenderAccountDto.builder().accountNumber("senderNo").build());
        transferDto.setReceiver(ReceiverAccountDto.builder().accountNumber("receiverNo").build());

        Mockito.when(transferRepository.findUserBySenderAccountNumber(Mockito.anyString())).thenReturn(user);

        Assertions.assertDoesNotThrow(() -> transferService.makeTransfer(transferDto));

    }

    @Test
    void makeTransfer_failSameAccount() {

        TransferRequestDto<SenderAccountDto, ReceiverAccountDto> transferDto = new TransferRequestDto<>();
        transferDto.setAmount(BigDecimal.valueOf(100));
        transferDto.setCurrency(CurrencyEnum.AZN);
        transferDto.setSender(SenderAccountDto.builder().accountNumber("no").build());
        transferDto.setReceiver(ReceiverAccountDto.builder().accountNumber("no").build());

        Assertions.assertThrows(InvalidTransferException.class, () -> transferService.makeTransfer(transferDto));

    }

    @Test
    void makeTransfer_failNullReceiver() {

        TransferRequestDto<SenderAccountDto, ReceiverAccountDto> transferDto = new TransferRequestDto<>();
        transferDto.setAmount(BigDecimal.valueOf(100));
        transferDto.setCurrency(CurrencyEnum.AZN);
        transferDto.setSender(SenderAccountDto.builder().accountNumber("no").build());
        transferDto.setReceiver(ReceiverAccountDto.builder().build());

        Assertions.assertThrows(InvalidTransferException.class, () -> transferService.makeTransfer(transferDto));

    }

}
