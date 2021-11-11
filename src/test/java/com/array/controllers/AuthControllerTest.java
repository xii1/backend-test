package com.array.controllers;

import com.array.AbstractControllerTest;
import com.array.controllers.requests.LoginRequest;
import com.array.controllers.requests.RegisterRequest;
import com.array.entity.User;
import com.array.repositories.UserRepository;
import com.array.services.RedisService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author XIII
 */
class AuthControllerTest extends AbstractControllerTest {

    final String REGISTER_API = "/auth/register";
    final String LOGIN_API = "/auth/login";
    final String LOGOUT_API = "/auth/logout";

    final RegisterRequest registerRequest = new RegisterRequest("First Name", "Last Name", "test@gmail.com", "Test123");
    final LoginRequest loginRequest = new LoginRequest("test@gmail.com", "Test123");

    @MockBean
    UserRepository userRepository;

    @MockBean
    RedisService redisService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void testRegisterShouldBeSuccess() throws Exception {
        Mockito.when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_API).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.message", is("Register successfully")));
    }

    @Test
    void testRegisterShouldBeFailed() throws Exception {
        Mockito.when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(new User()));

        mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_API).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("The email is registered")));
    }

    @Test
    void testLoginShouldBeSuccess() throws Exception {
        final User userTest = new User();
        userTest.setEmail(loginRequest.getEmail());
        userTest.setPassword(passwordEncoder.encode(loginRequest.getPassword()));

        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userTest));

        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_API).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.message", is("Login successfully")))
                .andExpect(jsonPath("$.data.token").hasJsonPath())
                .andExpect(jsonPath("$.data.user").hasJsonPath());
    }

    @Test
    void testLoginShouldBeFailed() throws Exception {
        final User userTest = new User();
        userTest.setEmail(loginRequest.getEmail());
        userTest.setPassword(passwordEncoder.encode("Pass wrong"));

        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userTest));

        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_API).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("The email or password is not correct")));
    }

    @Test
    @WithMockUser
    void testLogoutShouldBeSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(LOGOUT_API)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.message", is("Logout successfully")));
    }

    @Test
    void testLogoutShouldBeUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(LOGOUT_API)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token"))
                .andExpect(status().isUnauthorized());
    }
}