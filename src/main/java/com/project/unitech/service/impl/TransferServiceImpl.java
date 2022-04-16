package com.project.unitech.service.impl;

import com.project.unitech.dto.BalanceRestrictionCheckDto;
import com.project.unitech.dto.ReceiverAccountDto;
import com.project.unitech.dto.SenderAccountDto;
import com.project.unitech.dto.request.TransferRequestDto;
import com.project.unitech.dto.response.TransferResponseDto;
import com.project.unitech.entity.Transfer;
import com.project.unitech.entity.User;
import com.project.unitech.exception.InsufficientBalanceException;
import com.project.unitech.exception.InvalidTransferException;
import com.project.unitech.repository.TransferRepository;
import com.project.unitech.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    public TransferResponseDto makeTransfer(TransferRequestDto<SenderAccountDto, ReceiverAccountDto> transferDto) {

        checkAndRespond(transferDto);

        User user = transferRepository.findUserBySenderAccountNumber(transferDto.getSender().getAccountNumber());

        Transfer transfer = Transfer.builder()
                .amount(transferDto.getAmount().intValue())
                .senderAccountNumber(transferDto.getSender().getAccountNumber())
                .receiverAccountNumber(transferDto.getReceiver().getAccountNumber())
                .user(user)
                .build();

        transferRepository.save(transfer);

        return TransferResponseDto.builder()
                .transferId(String.valueOf(transfer.getId()))
                .build();
    }

    public void checkAndRespond(TransferRequestDto<SenderAccountDto, ReceiverAccountDto> transferDto) {

        if (transferDto.getSender().getAccountNumber().equals(transferDto.getReceiver().getAccountNumber())) {
            throw new InvalidTransferException();
        }

        if (transferDto.getReceiver().getAccountNumber() == null) {
            throw new InvalidTransferException();
        }

        BalanceRestrictionCheckDto balanceRestrictionCheckDTO = BalanceRestrictionCheckDto.builder()
                .transferAmount(transferDto.getAmount())
                .transferCurrency(transferDto.getCurrency())
                .build();

        checkBalanceSufficiency(balanceRestrictionCheckDTO);
    }

    public void checkBalanceSufficiency(BalanceRestrictionCheckDto balanceCheckDto) {

        if (balanceCheckDto.getSenderInfoDTO() == null) {
            return;
        }

        if (balanceCheckDto.getTransferAmount().compareTo(balanceCheckDto.getSenderInfoDTO().getSenderBalance()) > 0) {
            throw new InsufficientBalanceException();
        }

        BigDecimal remainingBalance = balanceCheckDto.getSenderInfoDTO().getSenderBalance()
                .subtract(balanceCheckDto.getTransferAmount());

        if (remainingBalance.compareTo(ZERO) < 0) {
            throw new InsufficientBalanceException();
        }
    }
}
