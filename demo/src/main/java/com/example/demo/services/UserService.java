package com.example.demo.services;

import com.example.demo.dtos.RegisterRequest;
import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.UserResponse;
import com.example.demo.dtos.builders.UserBuilder;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleName;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor


public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;




    public List<UserDTO> findUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserResponse saveUser(RegisterRequest req) {
        if (userRepository.existsByEmailIgnoreCase(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        User u = User.builder()
                .username(req.getUsername())
                .email(req.getEmail().toLowerCase())
                .password(req.getPassword())
                .role(userRole)
                .build();
        userRepository.save(u);
        return new UserResponse(u.getId(), u.getEmail(), u.getRole().getRoleName().name());

    }

    public void deleteUser(String email) {

    }






}
