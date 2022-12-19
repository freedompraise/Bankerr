package com.bankaccount.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class MoneyDebitedEvent {

    private final UUID accountId;
    private final String owner;
    private final BigDecimal debitAmount;

}
