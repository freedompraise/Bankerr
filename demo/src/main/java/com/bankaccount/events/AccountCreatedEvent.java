package com.bankaccount.events;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountCreatedEvent {

    private final UUID accountId;
    private final String owner;
    private final BigDecimal initialBalance;


}
