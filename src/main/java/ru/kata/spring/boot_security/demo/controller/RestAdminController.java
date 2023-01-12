package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.MyUserService;
import ru.kata.spring.boot_security.demo.service.RolesService;

import java.security.Principal;
import java.util.List;

@RestController
public class RestAdminController {
    private final MyUserService myUserService;
    private final RolesService rolesService;
    @Autowired
    public RestAdminController(MyUserService myUserService, RolesService rolesService) {
        this.myUserService = myUserService;
        this.rolesService = rolesService;
    }
    @GetMapping("/api/users")
    public List<UserEntity> getUsers() {
        return myUserService.allUsers();
    }

    @GetMapping("/api/roles")
    public List<Role> getRoles() {
        return rolesService.findAllRoles();
    }
    @PostMapping("/api/users")
    public UserEntity addNewUser(@RequestBody UserEntity user) {
        myUserService.saveUser(user);
        return user;
    }

    @PutMapping("/api/users")
    public UserEntity updateUser(@RequestBody UserEntity user) {
        myUserService.saveUser(user);
        return user;
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        UserEntity user = myUserService.findById(id);
        if (user == null) {
            throw  new NoSuchUserException("No user with ID = " + id + " in database");
        }
        myUserService.deleteUser(id);
        return "User with ID=" + id + " was deleted";
    }

    @GetMapping("/api/currentuser")
    public UserEntity getCurrentUser(Principal principal) {
        UserEntity loggedInUser = myUserService.findByUserName(principal.getName());
        return loggedInUser;
    }

}
