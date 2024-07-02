package com.kvk.postcode.postalcode.controller;

import com.kvk.postcode.postalcode.dao.PostalCodeDAO;
import com.kvk.postcode.postalcode.service.PostalCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//lets only test the controller
@WebMvcTest({PostalCodeController.class})
class PostalCodeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostalCodeService postalCodeService;


    @Test
    void expect200FromGet() throws Exception {
       this.mockMvc.perform(get("/postal-code/NL")).andExpect(status().isOk());
    }

    @Test
    void expect404FromGet() throws Exception {
        this.mockMvc.perform(get("/postal-code/")).andExpect(status().isNotFound());
    }

    @Test
    void expectPostalCodeObjectFromGet() throws Exception {
        when(postalCodeService.getPostalCodeDetails("NL")).thenReturn(new PostalCodeDAO("Netherlands", "#### @@", "^(\\d{4}[A-Z]{2})$"));
        this.mockMvc.perform(get("/postal-code/NL"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"countryName\":\"Netherlands\",\"format\":\"#### @@\",\"regex\":\"^(\\\\d{4}[A-Z]{2})$\"}"))
                .andReturn();
    }

    @Test
    void expect200FromPost() throws Exception {
        this.mockMvc.perform(post("/postal-code/NL")).andExpect(status().isOk());
    }

    @Test
    void expect404FromPost() throws Exception {
        this.mockMvc.perform(get("/postal-code/")).andExpect(status().isNotFound());
    }

    @Test
    void expectPostalCodeObjectFromPost() throws Exception {
        when(postalCodeService.addPostalCode("NL")).thenReturn(new PostalCodeDAO("Netherlands", "#### @@", "^(\\d{4}[A-Z]{2})$"));
        this.mockMvc.perform(post("/postal-code/NL"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"countryName\":\"Netherlands\",\"format\":\"#### @@\",\"regex\":\"^(\\\\d{4}[A-Z]{2})$\"}"))
                .andReturn();
    }
}
