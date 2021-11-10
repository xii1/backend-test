package com.array.controllers;

import com.array.entity.User;
import com.array.entity.enums.Role;
import com.array.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Api Services is running";
    }

    @PostMapping("/test")
    public ResponseEntity<?> register() {

        final User user = new User();
        user.setEmail("test1@gmail.com");
        user.setFirstName("Test1");
        user.setLastName("TestTest");
        user.setPassword(passwordEncoder.encode("abc"));
        user.setRoles(Stream.of(Role.CUSTOMER).collect(Collectors.toSet()));

        return ResponseEntity.ok(userService.createUser(user));
    }
}
