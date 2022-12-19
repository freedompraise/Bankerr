package com.bankaccount.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditMoneyCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private String owner;
    private BigDecimal creditAmount;


    public CreditMoneyCommand(String format, BigDecimal amount) {
    }
}
