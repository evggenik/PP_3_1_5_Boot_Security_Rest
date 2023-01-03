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
    private final RolesService rolesService;

    @Autowired
    public AdminController(MyUserService myUserService, RolesService rolesService) {
        this.myUserService = myUserService;
        this.rolesService = rolesService;
    }
    @GetMapping("/admin")
    public String getUserName(Principal principal, Model model) {
        UserEntity loggedInUser = myUserService.findByUserName(principal.getName());
        List<Role> roleList = myUserService.getRoles();
        model.addAttribute("roleList", roleList);
        model.addAttribute("users", myUserService.allUsers());
        model.addAttribute("userLoggedIn", loggedInUser);
        model.addAttribute("newUser", new UserEntity());
        model.addAttribute("users_roles", rolesService.findAllRoles());
        return "admin";
    }


    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute UserEntity user) {
        user.setRoles(user.getRoles());
        myUserService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        myUserService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") UserEntity user) {
//        System.out.println(user);
        myUserService.saveUser(user);
        return "redirect:/admin";
    }
}
