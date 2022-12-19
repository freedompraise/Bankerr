package com.bankaccount;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(UUID accountId, String owner, BigDecimal debitAmount) {
        super("Insufficient Funds: Cannot debit" + debitAmount + " from [" + owner + "]" );
    }
}
