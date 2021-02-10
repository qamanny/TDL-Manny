package com.qa.rest.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import com.qa.dto.TaskDTO;
import com.qa.persistence.domain.Task;
import com.qa.persistence.domain.repos.TaskRepo;

@SpringBootTest
public class TaskServiceUnitTest {
	
	@MockBean
	private ModelMapper mockedMapper;
	
	@MockBean
	private TaskRepo mockedRepo;
	
	@Autowired
	private TaskService service;

	// Get Test

	@Test
	public void readAll() {
	}

	@Test
	public void readOne() throws ParseException {
		
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");
		
		Task TEST_TASK = new Task(1L, "Certificate on freecodeCamp", date, false, null);
		TaskDTO TEST_DTO = this.mockedMapper.map(TEST_TASK, TaskDTO.class);
		
		//Rules
		Mockito.when(this.mockedRepo.findById(TEST_TASK.getId())).thenReturn(Optional.of(TEST_TASK));
		
		//Actions
		TaskDTO result = this.service.readOne(1L);
		
		//Assertions
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		
		//Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}
 
	// Post Test 

	@Test
	public void create() throws ParseException {
		
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");
		
		Task TEST_TASK = new Task(1L, "Certificate on freecodeCamp", date, false, null);
		
		//Rules
		Mockito.when(this.mockedRepo.save(Mockito.any(Task.class))).thenReturn(TEST_TASK);
		
		//Actions
		TaskDTO result = this.service.create(TEST_TASK);
		
		
		//Assertions
		Assertions.assertThat(result).isEqualTo(this.mockedMapper.map(TEST_TASK, TaskDTO.class));
		Assertions.assertThat(result)
		.usingRecursiveComparison()
		.isEqualTo(this.mockedMapper.map(TEST_TASK, TaskDTO.class));
		
		//Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(Task.class));
	
	}

	// Put Test
	
	@Test
	public void update() {
	}

	// Delete Test

	@Test
	public void delete() {

	}

}
