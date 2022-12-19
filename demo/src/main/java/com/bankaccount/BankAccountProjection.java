package com.bankaccount;

import com.bankaccount.entities.Account;
import com.bankaccount.events.AccountCreatedEvent;
import com.bankaccount.events.MoneyCreditedEvent;
import com.bankaccount.events.MoneyDebitedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class BankAccountProjection {

    private  final BankAccountRepository repository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
//        log.debug("Handling a Bank Account creation command {}", event.getAccountId());
        Account account = new Account(

                event.getAccountId(),
                event.getOwner(),
                event.getInitialBalance()
        );
        this.repository.save(account);
    }

    @EventHandler
    public void on(MoneyCreditedEvent event) throws AccountNotFoundException{
        Optional<Account> optionalAccount = this.repository.findById(event.getAccountId());
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance().add(event.getCreditAmount()));
            this.repository.save(account);
        }else {
            throw new AccountNotFoundException(event.getOwner());
        }
    }

    @EventHandler
    public void on(MoneyDebitedEvent event)throws AccountNotFoundException {
        Optional <Account> optionalAccount = this.repository.findById(event.getAccountId());
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance().subtract(event.getDebitAmount()));
            this.repository.save(account);
        }else {
            throw new AccountNotFoundException(event.getOwner());
        }
    }

    @QueryHandler
    public Account handle(FindBankAccountQuery query) {
        return this.repository.findById(query.getAccountId()).orElse(null);
    }
}
