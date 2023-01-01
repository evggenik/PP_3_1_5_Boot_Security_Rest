package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

}
