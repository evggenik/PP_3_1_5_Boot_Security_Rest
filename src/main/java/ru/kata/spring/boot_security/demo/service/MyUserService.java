package ru.kata.spring.boot_security.demo.service;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import ru.kata.spring.boot_security.demo.model.MyUserDetails;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
//import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class MyUserService {
//public class MyUserService implements UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public MyUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> allUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    public void deleteUser(Long userId) {
            userRepository.deleteById(userId);
    }

    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName).get();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

}



//    public boolean createUser(UserEntity user) {
//        String userName = user.getUsername();
//        if (userRepository.findByUserName(userName) != null) return false;
//        user.setActive(true);
//        user.getRoles().add(Role.ROLE_USER);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<UserEntity> user = userRepository.findByUserName(userName);
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + userName));
//        return user.map(MyUserDetails::new).get();
//    }
//
//    public List<UserEntity> allUsers() {
//        return userRepository.findAll();
//    }
//
//    public UserEntity saveUser(UserEntity user) {
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(Integer userId) {
//            userRepository.deleteById(userId);
//    }
//}
