package com.array.controllers;

import com.array.controllers.helpers.RestConstants;
import com.array.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Web Service is running";
    }

    @GetMapping("/admin")
    @PreAuthorize(RestConstants.Authorization.HAS_AUTHORITY_ADMIN)
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/customer")
    @PreAuthorize(RestConstants.Authorization.HAS_ANY_AUTHORITY_ADMIN_CUSTOMER)
    public ResponseEntity<?> customer() {
        return ResponseEntity.ok("OK");
    }
}
