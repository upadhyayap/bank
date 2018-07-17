package com.banking.bank.domain;

import lombok.Data;

@Data
public class Account {
    Long id;
    Double balance;
    String name;
    String email;
    long phoneNo;
}
