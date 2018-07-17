package com.banking.bank.controller;

import com.banking.bank.domain.Resource;
import com.banking.bank.domain.User;
import com.banking.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{userId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> find(@PathVariable("userId") long userId) {
        User user = userService.findUser(userId);
        HttpStatus status = user!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_VALUE
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> create(@RequestBody User user){
        Long id = userService.createUser(user);
        HttpStatus status = id != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new Resource(id), status);
    }
}
