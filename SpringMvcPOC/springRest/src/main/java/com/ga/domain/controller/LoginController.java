package com.ga.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.persistence.entity.User;
import com.ga.service.LoginService;
import com.ga.service.UserService;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password) {

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        Boolean status = loginService.checkLogin(user);

        String msg;

        if (status == true) {
            msg = "login successfull";

        } else {
            msg = "wrong user name and password";
        }

        System.out.println(msg);

        return msg;
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @RequestMapping(value = "getUserDetails/{userName}", method = RequestMethod.GET)
    public User getAllUsers(@PathVariable("userName")String userName) {
        return userService.getUserDetails(userName);
    }
}
