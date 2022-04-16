package com.project.unitech.service.impl;

import com.project.unitech.dto.AccountDto;
import com.project.unitech.dto.request.AccountRequestDto;
import com.project.unitech.entity.User;
import com.project.unitech.entity.UserAccount;
import com.project.unitech.enums.RegistrationStatus;
import com.project.unitech.exception.UserNotFoundException;
import com.project.unitech.repository.AccountRepository;
import com.project.unitech.service.AccountService;
import com.project.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;

    @Override
    public List<UserAccount> getUserAccounts(AccountRequestDto accountRequestDto) {

        User user = userService.findUserByPin(accountRequestDto.getPin()).orElseThrow(UserNotFoundException::new);

        List<UserAccount> userAccounts = accountRepository.findAccountsByUserId(user.getId());

        List<UserAccount> userActiveAccounts = new ArrayList<>();

        for(UserAccount userAccount: userAccounts) {
            if (userAccount.getStatus() == RegistrationStatus.ACTIVE) {
                userActiveAccounts.add(userAccount);
            }
        }
        return userActiveAccounts;

    }

}
