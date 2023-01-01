package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Optional<Role> findByName(String name);
//    public List<Role> findAllById(int user_id);

}
