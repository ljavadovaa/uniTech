package com.project.unitech.service;

import com.project.unitech.dto.request.AccountRequestDto;
import com.project.unitech.entity.User;
import com.project.unitech.entity.UserAccount;
import com.project.unitech.enums.RegistrationStatus;
import com.project.unitech.repository.AccountRepository;
import com.project.unitech.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getUserAccounts() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        UserAccount userAccount = UserAccount.builder()
                .status(RegistrationStatus.ACTIVE)
                .build();

        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setPin("AAA1111");

        Mockito.when(userService.findUserByPin(Mockito.anyString())).thenReturn(Optional.of(user));

        Mockito.when(accountRepository.findAccountsByUserId(Mockito.any())).thenReturn(List.of(userAccount));

        Assertions.assertDoesNotThrow(() -> accountService.getUserAccounts(accountRequestDto));

    }

}
