package edu.mondragon.we2.pinkAlert.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LoginControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders
                .standaloneSetup(new LoginController())
                .setViewResolvers(viewResolver)
                .build();
    }

    // -------- GET --------

    @Test
    void testShowLoginPageFromRoot() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testShowLoginPageFromLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    // -------- POST SUCCESS --------

    @Test
    void testDoctorLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "doctor")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/doctor/dashboard"));
    }

    @Test
    void testPatientLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "patient")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/patient/portal"));
    }

    @Test
    void testAdminLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "admin")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/dashboard"));
    }

    // -------- POST FAIL --------

    @Test
    void testInvalidLogin() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "bad")
                        .param("password", "bad"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error",
                        "Invalid username or password."));
    }
}
