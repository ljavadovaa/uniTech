package com.project.unitech.service;

import com.project.unitech.dto.AccountDto;
import com.project.unitech.dto.request.AccountRequestDto;
import com.project.unitech.entity.UserAccount;

import java.util.List;

public interface AccountService {

    List<UserAccount> getUserAccounts(AccountRequestDto accountRequestDto);

}
