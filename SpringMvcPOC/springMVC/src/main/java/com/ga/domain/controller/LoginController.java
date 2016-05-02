package com.ga.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ga.persistence.entity.User;
import com.ga.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome() {

        ModelAndView mv = new ModelAndView("index");
        User user = new User();
        mv.addObject("user", user);
        return mv;

    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("user") User user) {

        System.out.println(user.getUserName() + "--" + user.getPassword());
        ModelAndView mv = null;
        Boolean status =  loginService.checkLogin(user);
        
        if(status == true){
            
            mv = new ModelAndView("Home");
            
        }else{
            
            mv = new ModelAndView("index");
            mv.addObject("msg", "Wrong User Name Or Password");
        }
        
        return mv;
    }

}
