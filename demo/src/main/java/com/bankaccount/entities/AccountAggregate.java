package com.bankaccount.entities;

import com.bankaccount.InsufficientBalanceException;
import com.bankaccount.commands.CreateAccountCommand;
import com.bankaccount.commands.CreditMoneyCommand;
import com.bankaccount.commands.DebitMoneyCommand;
import com.bankaccount.events.AccountCreatedEvent;
import com.bankaccount.events.MoneyCreditedEvent;
import com.bankaccount.events.MoneyDebitedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private UUID accountId;
    private String owner;
    private BigDecimal balance;


    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd) {

        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        cmd.getAccountId(),
                        cmd.getOwner(),
                        cmd.getInitialBalance()
                )

        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
        this.owner = event.getOwner();
        this.balance = event.getInitialBalance();
    }

    @CommandHandler
    public void handle(CreditMoneyCommand cmd) {
        AggregateLifecycle.apply(
                new MoneyCreditedEvent(
                      cmd.getAccountId(),
                      cmd.getOwner(),
                      cmd.getCreditAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent event) {
        this.balance.add(event.getCreditAmount());
    }

    @CommandHandler
    public void handle(DebitMoneyCommand cmd) {
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(
                        cmd.getAccountId(),
                        cmd.getOwner(),
                        cmd.getDebitAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent event) throws InsufficientBalanceException {
        if (this.balance.compareTo(event.getDebitAmount()) < 0){
            throw new InsufficientBalanceException(event.getAccountId(), event.getOwner(), event.getDebitAmount());
        }
        this.balance = this.balance.subtract(event.getDebitAmount());
    }
}
