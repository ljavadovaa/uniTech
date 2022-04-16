package com.project.unitech.controller;

import com.project.unitech.dto.request.AccountRequestDto;
import com.project.unitech.entity.UserAccount;
import com.project.unitech.enums.RegistrationStatus;
import com.project.unitech.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void getAccounts() {

        UserAccount userAccount = UserAccount.builder()
                .status(RegistrationStatus.ACTIVE)
                .build();

        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setPin("AAA11111");

        Mockito.when(accountService.getUserAccounts(Mockito.any())).thenReturn(List.of(userAccount));

        Assertions.assertDoesNotThrow(() -> accountController.getAccounts(accountRequestDto));
    }


}
