package com.bankaccount.controllers;

import com.bankaccount.dto.AccountCreationDTO;
import com.bankaccount.dto.MoneyAmountDTO;
import com.bankaccount.entities.Account;
import com.bankaccount.services.AccountCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/accounts")
@Api(value = "Account Commands")
@AllArgsConstructor
public class AccountCommandController {
    private final AccountCommandService accountCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create an account")
    public CompletableFuture<Account> createAccount(@RequestBody AccountCreationDTO creationDTO){
        return this.accountCommandService.createAccount(creationDTO);
    }

    @PutMapping(value = "/credit/{accountId}")
    @ApiOperation(value = "credit an account")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                         @RequestBody MoneyAmountDTO moneyCreditDTO) {
                                                    return this.accountCommandService.creditMoneyToAccount(accountId, moneyCreditDTO);
    }

    @PutMapping(value = "/debit/{accountId}")
    @ApiOperation(value = "debit money from an account")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountId") String accountId,
                                                           @RequestBody MoneyAmountDTO moneyDebitDTO) {
        return this.accountCommandService.debitMoneyFromAccount(accountId, moneyDebitDTO);
    }
}
