package com.qa.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dto.TaskDTO;
import com.qa.persistence.domain.Task;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private final int ID = 1;
	
	private TaskDTO mapToDTO(Task model) {
		return this.mapper.map(model, TaskDTO.class);
	}
	
	// Get Test

	@Test
	public void readAll() {

	}

	@Test
	public void readTask() throws Exception {
		
		//Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");
		
		Task contentBody = new Task(6L, "Increase Chess Elo Rating", date, false, null);
		TaskDTO expectedResult = mapToDTO(contentBody);
		
		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/task/create/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		
		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		// Perform Actions
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// Post Test

	@Test
	public void create() throws Exception {
		
		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");
		
		TaskDTO expectedResult = new TaskDTO(1L, "Certificate on freecodeCamp", date, false);
		
		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "http://localhost:8080/task/read/" + ID);
		
		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		// Perform Actions
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// Put Test

	@Test
	public void update() throws Exception {
	}

	// Delete Test

	@Test
	public void delete() throws Exception {
	}
}