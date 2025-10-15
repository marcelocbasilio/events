package com.mar.events.controllers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mar.events.dtos.EmployeeDTO;
import com.mar.events.dtos.EventDTO;
import com.mar.events.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EventControllerIT {

    @Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private String operatorUsername;
	private String operatorPassword;
	private String adminUsername;
	private String adminPassword;

    @BeforeEach
	void setUp() throws Exception {
		
		operatorUsername = "ana@gmail.com";
		operatorPassword = "123456";
		adminUsername = "bob@gmail.com";
		adminPassword = "123456";
	}

    @Test
	public void insertShouldReturn401WhenNoUserLogged() throws Exception {

		EventDTO dto = new EventDTO(null, "Java AI", LocalDate.now(), "https://javaai.com",1L);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/events")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnauthorized());
	}

	@Test
	void insertShouldReturn201WhenClientLoggedAndValidData() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, operatorUsername, operatorPassword);
		EventDTO dto = new EventDTO(null, "Java AI", LocalDate.now(), "https://javaai.com", 1L);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/events")
				    .header("Authorization", "Bearer " + accessToken)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());

	}

	@Test
	void insertShouldReturn201WhenAdminLoggedAndValidData() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
		EventDTO dto = new EventDTO(null, "Java AI", LocalDate.now(), "https://javaai.com", 1L);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/events")
				    .header("Authorization", "Bearer " + accessToken)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());

	}

	@Test
	void insertShouldReturn422WhenAdminLoggedAndNameBlank() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
		EventDTO dto = new EventDTO(null, "   ", LocalDate.now(), "https://javaai.com", 1L);
		String jsonBody = objectMapper.writeValueAsString(dto);

		ResultActions result =
				mockMvc.perform(post("/events")
				    .header("Authorization", "Bearer " + accessToken)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnprocessableEntity());
	}
}
