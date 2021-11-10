package com.array.controllers;

import com.array.controllers.helpers.PathConstants;
import com.array.controllers.requests.LoginRequest;
import com.array.controllers.requests.RegisterRequest;
import com.array.controllers.responses.JwtTokenResponse;
import com.array.entity.User;
import com.array.entity.enums.Role;
import com.array.services.RedisService;
import com.array.services.UserService;
import com.array.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author XIII
 */
@RestController
@RequestMapping(PathConstants.Authentication.PREFIX)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostMapping(PathConstants.Authentication.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.checkExists(registerRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("The email is registered");
        }

        final User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Stream.of(Role.CUSTOMER).collect(Collectors.toSet()));

        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping(PathConstants.Authentication.LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            final User user = (User) authentication.getPrincipal();
            final String jwtToken = jwtTokenUtils.generateJwtToken(user);
            return ResponseEntity.ok(new JwtTokenResponse(jwtToken, user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest()
                    .body("The email or password is not correct");
        }
    }

    @PostMapping(PathConstants.Authentication.LOGOUT)
    public ResponseEntity<?> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        final String token = authHeader.split(" ")[1].trim();
        redisService.put(token, "revoked");

        return ResponseEntity.ok("The email or password is not correct");
    }
}