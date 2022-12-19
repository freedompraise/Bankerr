package com.bankaccount.controllers;

import com.bankaccount.entities.Account;
import com.bankaccount.services.AccountQueryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Account queries")
@AllArgsConstructor
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/{accountId")
    public CompletableFuture<Account> findById(@PathVariable("accountId") String accountId ){
        return this.accountQueryService.findById(accountId);
    }

    @GetMapping("/{accountId}/events")
    public java.util.List<Object> ListEventsForAccount(@PathVariable(value = "accountId") String accountId) {
        return this.accountQueryService.listEventsForAccount(accountId);
    }
}
