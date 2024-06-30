package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankTransfer {
    private String fromAccount;
    private String toAccount;
    private Long amount;
}
