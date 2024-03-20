package com.project.bookmanagement.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookmanagement.Dtos.RegistrationDto;
import com.project.bookmanagement.entity.User;
import com.project.bookmanagement.entity.UserRole;
import com.project.bookmanagement.repository.UserRepository;
import com.project.bookmanagement.repository.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(RegistrationDto userDto) throws Exception {
        if(userDto==null) {
            throw new Exception("User should not be null");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Set<UserRole> userRoles = new HashSet<>();

        for (String roleName : userDto.getRoles()) {
            UserRole role = userRoleRepository.findByName(roleName);
            if (role != null) {
                userRoles.add(role);
            } else {
                throw new Exception("role not found");
            }
        }
        user.setRoles(userRoles);
        
        user.setRoles(userRoles);
        User db_user = userRepository.findByEmail(user.getEmail());
        System.out.println(db_user);
        if(db_user==null) {
            user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(user);
        }
        else throw new Exception("User already exists");
    }
    

    @Override
    public User getUserById(Long id) throws Exception {
        if(id==null) {
            throw new Exception("User id should not be null");
        }
        return userRepository.findById(id).get();
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Boolean checkPassword(String plainPassword,String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
