package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.MyUserService;

import java.security.Principal;


@Controller
public class UserController {

    private MyUserService myUserService;
    @Autowired
    public UserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        UserEntity user = myUserService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
