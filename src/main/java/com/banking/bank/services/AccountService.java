package com.banking.bank.services;


import com.banking.bank.domain.Account;
import com.banking.bank.domain.Resource;
import com.banking.bank.domain.Transaction;
import com.banking.bank.domain.repository.AccountRepository;
import com.banking.bank.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class AccountService {

    public AccountRepository accountRepository;
    public TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Resource open(Account account) {
        return new Resource();
    }

    public Double getBalanceInfo(Long accountNo) {
        return 0d;
    }

    public boolean addPayee(Map payeeInfo) {
        return true;
    }

    public boolean deletePayee(Map payeeInfo){
        return true;
    }

    public List<Transaction> getTransactionHistory(long accountNo){
        return new ArrayList<>();
    }

    public Transaction transferInstant(Long payerAccNo, Long payeeAccNo, Double amount){
        return new Transaction();
    }

    public boolean scheduleTransfer(Long payerAccNo,Map<String, Object> transactInfo){
        return true;
    }

    public Double getFutureBalance(Long AccountNo, Date date){
        return 0d;
    }

}
