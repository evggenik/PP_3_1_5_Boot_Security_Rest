package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;

import ru.kata.spring.boot_security.demo.service.MyUserService;
import ru.kata.spring.boot_security.demo.service.RolesService;

import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping("/")
public class AdminController {

    private final MyUserService myUserService;

    @Autowired
    public AdminController(MyUserService myUserService) {
        this.myUserService = myUserService;

    }

    @GetMapping("/admin")
    public String getUserName(Principal principal, Model model) {
        UserEntity loggedInUser = myUserService.findByUserName(principal.getName());
        model.addAttribute("userLoggedIn", loggedInUser);
        return "admin";
    }


    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute UserEntity user) {
        user.setRoles(user.getRoles());
        myUserService.saveUser(user);
        return "redirect:/admin";
    }

}
