package com.banking.bank.dao;

import lombok.Data;

@Data
public class Resource {
    private long id;

    public Resource(){}
    public Resource(long id){
        this.id = id;
    }
}
