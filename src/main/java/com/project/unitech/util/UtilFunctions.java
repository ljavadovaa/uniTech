package com.project.unitech.util;

import com.project.unitech.dto.SenderAccountDto;
import com.project.unitech.dto.request.TransferRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilFunctions {

    public static final Function<TransferRequestDto<?, ?>, String> detectSenderAccountNum = transferDto -> {
        if (transferDto.getSender() instanceof SenderAccountDto) {
            SenderAccountDto sender = (SenderAccountDto) transferDto.getSender();
            return sender.getAccountNumber();
        }
        return null;
    };
}
