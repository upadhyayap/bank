package com.banking.bank.controller;


import com.banking.bank.domain.Account;
import com.banking.bank.domain.Resource;
import com.banking.bank.domain.Transaction;
import com.banking.bank.services.AccountService;
import com.banking.bank.services.TransactionService;
import com.banking.bank.util.status.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    public AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/open", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> create(@RequestBody Account account) {
        Resource resource = accountService.open(account);
        HttpStatus status = resource != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(resource, status);
    }

    @RequestMapping(value = "/{accountNo}/balance", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> getBalance(@PathVariable("accountNo") long accountNo) {
        // we can first validate the Account no and if not valid throw an exception
        // skipping it due to time constraint
        Double balance = accountService.getBalanceInfo(accountNo);
        HashMap resStatus = new HashMap();
        resStatus.put("balance", balance);
        HttpStatus status = balance != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity(resStatus, status);
    }

    @RequestMapping(value = "/{accountNo}/addpayee",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResStatus> addPayee(@PathVariable("accountNo") Long accountNo, @RequestBody Map<String, Object> payeeInfo) {
        ResStatus resStatus = new ResStatus();
        boolean status = accountService.addPayee(payeeInfo);
        if (status)
            resStatus.setStatus("Success");
        else
            resStatus.setStatus("failed");

        return new ResponseEntity(resStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountNo}/droppayee",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePayee(@PathVariable("accountNo") Long accountNo, @RequestBody Map<String, Object> payeeInfo) {
        ResStatus resStatus = new ResStatus();
        boolean status = accountService.deletePayee(payeeInfo);
        if (status)
            resStatus.setStatus("Success");
        else
            resStatus.setStatus("failed");

        return new ResponseEntity(resStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountNo}/history",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> transactionHistory(@PathVariable("accountNo") Long accountNo) {
        List<Transaction> history = accountService.getTransactionHistory(accountNo);

        return new ResponseEntity<List<Transaction>>(history, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountNo}/transferInstant",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> transferInstant(@PathVariable("accountNo") Long accountNo, @RequestBody Map<String, Object> transferInfo) {
        // catch number format exception and throw error
        Long payeeAccNo = Long.parseLong(transferInfo.get("payerAccNo").toString());
        Double amount = Double.parseDouble(transferInfo.get("amount").toString());
        Transaction transaction = accountService.transferInstant(accountNo, payeeAccNo, amount);

        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountNo}/scheduletransfer",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResStatus> scheduleTransact(@PathVariable("accountNo") Long accountNo, @RequestBody Map<String, Object> transferInfo) {
        ResStatus resStatus = new ResStatus();
        boolean transactStatus = accountService.scheduleTransfer(accountNo, transferInfo);

        if (transactStatus)
            resStatus.setStatus("Success");
        else
            resStatus.setStatus("failed");

        return new ResponseEntity<ResStatus>(resStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountNo}/balance/future", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Double>> getBalanceForFutureDate(@PathVariable("accountNo") Long accountNo, @RequestParam("date")
    @DateTimeFormat(pattern = "dd-mm-yyyy") Date toDate) {
        Double balance = accountService.getFutureBalance(accountNo, toDate);
        HashMap resStatus = new HashMap();
        resStatus.put("balance", balance);

        return new ResponseEntity<>(resStatus, HttpStatus.OK);
    }

}
