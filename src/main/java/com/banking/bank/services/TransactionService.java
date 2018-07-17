package com.banking.bank.services;


import com.banking.bank.domain.Account;
import com.banking.bank.domain.Transaction;
import com.banking.bank.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionService {

    public TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionHistory(long accountNo){
        return new ArrayList<>();
    }

    public Transaction transferInstant(Account payer, Account payee){
        return new Transaction();
    }

    public boolean scheduleTransfer(Account payer, Account payee){
        return true;
    }
}
