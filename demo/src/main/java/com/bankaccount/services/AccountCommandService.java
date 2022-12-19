package com.bankaccount.services;

import com.bankaccount.commands.CreateAccountCommand;
import com.bankaccount.commands.CreditMoneyCommand;
import com.bankaccount.commands.DebitMoneyCommand;
import com.bankaccount.dto.AccountCreationDTO;
import com.bankaccount.dto.MoneyAmountDTO;
import com.bankaccount.entities.Account;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<Account> createAccount(AccountCreationDTO creationDTO) {
        return  this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID(),
                creationDTO.getInitialBalance(),
                creationDTO.getOwner()
        ));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountId,
                                                          MoneyAmountDTO moneyCreditDTO){
        return this.commandGateway.send(new CreditMoneyCommand(
                format(accountId),
                moneyCreditDTO.getAmount()
        ));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountId,
                                                           MoneyAmountDTO moneyDebitDTO){
        return this.commandGateway.send(new DebitMoneyCommand(
                format(accountId),
                moneyDebitDTO.getAmount()
        ));
    }
}
