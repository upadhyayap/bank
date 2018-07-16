package com.banking.bank.services;


import com.banking.bank.dao.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public User findUser(long userId) {
        User user = new User();
        user.setName("Anand");

        return user;
    }

    public long createUser(User user){
        return user.getId();
    }

    public String deleteUser(long userId) {
        return "userId";
    }

    public String modifyUser(String userInfo) {
        return "modified";
    }
}
