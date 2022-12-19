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
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private String owner;
    private BigDecimal initialBalance;

    public CreateAccountCommand(UUID accountId, BigDecimal initialBalance, String owner) {
    }
}
