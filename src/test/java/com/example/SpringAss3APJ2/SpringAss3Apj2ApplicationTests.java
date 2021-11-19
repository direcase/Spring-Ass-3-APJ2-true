package com.example.SpringAss3APJ2;

import com.example.SpringAss3APJ2.controller.BankController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringAss3Apj2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Baby Shark")));
	}

	@Test
	 void accessTest() throws Exception{
		this.mockMvc.perform(get("/history"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	void login() throws Exception{
		this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
				.user("email@email.com").password("12345"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
	}

	@Test
	void badRequest() throws Exception{
		this.mockMvc.perform(post("/login").param("email", "email@email.com"))
                .andDo(print())
				.andExpect(status().is3xxRedirection());
	}

	@Test
	void changePass() throws Exception{
		this.mockMvc.perform(get("/user/changePassword/4"))
				.andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void profile() throws Exception{
		this.mockMvc.perform(get("/profile/4"))
				.andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
}
