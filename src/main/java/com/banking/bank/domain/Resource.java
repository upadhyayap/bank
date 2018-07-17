package com.banking.bank.domain;

import lombok.Data;

@Data
public class Resource {
    private long id;
    private String status;

    public Resource(){}
    public Resource(long id){
        this.id = id;
    }
}
