package com.array.controllers;

import com.array.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author XIII
 */
class HomeControllerTest extends AbstractControllerTest {

    @Test
    void testHomeShouldBeSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Web Service is running"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void testAdminShouldBeSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER"})
    void testAdminShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"CUSTOMER"})
    void testCustomerShouldBeSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}