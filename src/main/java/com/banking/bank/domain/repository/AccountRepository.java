package com.banking.bank.domain.repository;


import com.banking.bank.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Long>{

}
