package com.example.SpringAss3APJ2;

import com.example.SpringAss3APJ2.controller.BankController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultHandlers;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("email@email.com")
class BankControllerTest {

    @Autowired
    private BankController bankController;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void payment() throws Exception{
        this.mockMvc.perform(get("/payments"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
    @Test
    void history() throws Exception{
        this.mockMvc.perform(get("/history/4"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
    @Test
    void transaction() throws Exception{
        this.mockMvc.perform(get("/transfers-boot"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void paymentPost() throws Exception{
        this.mockMvc.perform(post("/payments/").param("CardNumber","1234123412")
                .param("numberTel","87478202172")
                .param("Amount","555"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void transactionPost() throws Exception{
        this.mockMvc.perform(post("/transfers-boot/").param("a","another bank")
                .param("cardN1","1234123412")
                .param("cardN2","2147483647")
                .param("amount","555"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}
