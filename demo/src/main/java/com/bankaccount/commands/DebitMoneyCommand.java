package com.bankaccount.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitMoneyCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private String owner;
    private BigDecimal debitAmount;

    public DebitMoneyCommand(String format, BigDecimal amount) {
    }
}
