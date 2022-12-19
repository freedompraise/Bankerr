package com.bankaccount.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountCreationDTO {
    BigDecimal initialBalance;
    String owner;
}
