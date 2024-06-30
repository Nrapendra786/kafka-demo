package org.example;

import lombok.Data;

@Data
public class BankTransfer {

    private String fromAmount;
    private String toAmount;
    private Long amount;
}
