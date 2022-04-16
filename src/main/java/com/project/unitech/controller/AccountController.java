package com.project.unitech.controller;

import com.project.unitech.dto.request.AccountRequestDto;
import com.project.unitech.dto.response.ApiResponseDto;
import com.project.unitech.entity.UserAccount;
import com.project.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ApiResponseDto<List<UserAccount>> getAccounts(@RequestBody AccountRequestDto accountRequestDto) {

        List<UserAccount> userAccounts = accountService.getUserAccounts(accountRequestDto);

        return ApiResponseDto.<List<UserAccount>>builder()
                .data(userAccounts)
                .message("success")
                .build();
    }
}
