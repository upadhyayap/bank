package com.banking.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    String home(){
        return "Home";
    }
}
