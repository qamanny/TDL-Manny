package com.qa.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.qa.dto.ProjectDTO;
import com.qa.dto.TaskDTO;
import com.qa.persistence.domain.Project;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ProjectControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;

	private final int ID = 1;
	private final String URL = "http://localhost:8080/project/";

	private ProjectDTO mapToDTO(Project model) {
		return this.mapper.map(model, ProjectDTO.class);
	}

	// Get Test

	@Test
	public void readAll() throws Exception {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");
		Date date2 = simply.parse("2021-02-10");
		Date date3 = simply.parse("2021-04-13");
		Date date4 = simply.parse("2021-02-09");
		Date date5 = simply.parse("2021-10-20");

		TaskDTO TEST = new TaskDTO(1L, "Certificate on freecodeCamp", date, false);
		TaskDTO TEST2 = new TaskDTO(2L, "Do 100 in a row Situps", date2, false);
		TaskDTO TEST3 = new TaskDTO(3L, "Find a gymnastics place", date3, false);
		TaskDTO TEST4 = new TaskDTO(4L, "Finish BackEnd and BackEnd Testing", date4, false);
		TaskDTO TEST5 = new TaskDTO(5L, "Finish Current Unit on Rosetta Stone", date5, false);
		
		
		List<TaskDTO> taskList = new ArrayList<>();
		taskList.add(TEST);
		List<TaskDTO> taskList2 = new ArrayList<>();
		taskList2.add(TEST2);
		List<TaskDTO> taskList3 = new ArrayList<>();
		taskList3.add(TEST3);
		List<TaskDTO> taskList4 = new ArrayList<>();
		taskList4.add(TEST4);
		List<TaskDTO> taskList5 = new ArrayList<>();
		taskList5.add(TEST5);
		

		ProjectDTO Project = new ProjectDTO(1L, "Learning Javascript", taskList);
		ProjectDTO Project2 = new ProjectDTO(2L, "Six Pack", taskList2);
		ProjectDTO Project3 = new ProjectDTO(3L, "Backflip", taskList3);
		ProjectDTO Project4 = new ProjectDTO(4L, "To Do List", taskList4);
		ProjectDTO Project5 = new ProjectDTO(5L, "Learning German", taskList5);
		List<ProjectDTO> Project_List = List.of(Project, Project2, Project3, Project4, Project5);

		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/project/readAll");

		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(Project_List));

		// Perform Actions
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readProject() throws Exception {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");

		TaskDTO TEST = new TaskDTO(1L, "Certificate on freecodeCamp", date, false);
		
		List<TaskDTO> taskList = new ArrayList<>();
		taskList.add(TEST);

		ProjectDTO expectedResult = new ProjectDTO(1L, "Learning Javascript", taskList);
		
		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/project/read/" + ID);

		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform Actions

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// Post Test

	@Test
	public void create() throws Exception {

		// Resources
		Project contentBody = new Project(6L, "Chess Champion", null);
		ProjectDTO expectedResult = mapToDTO(contentBody);

		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/project/create/").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);

		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform Actions

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// Put Test

	@Test
	public void update() throws Exception {

		// Resources
		Project alteredBody = new Project(null, "Biking", null);
		alteredBody.setId(1L);
		ProjectDTO expectedResult = mapToDTO(alteredBody);

		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/project/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(alteredBody))
				.accept(MediaType.APPLICATION_JSON);

		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform Actions
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}

	// Delete Test

	@Test
	public void delete() throws Exception {

		// Resources

		// Set Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				URL + "delete/" + 2);

		// Set Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// Perform Actions
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

}
