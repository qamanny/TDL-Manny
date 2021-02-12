package com.qa.rest.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;

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

	private TaskDTO mapToDTO(Task model) {
		return this.mockedMapper.map(model, TaskDTO.class);
	}

	// Get Test

	@Test
	public void readAll() throws ParseException {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-02-10");

		Task TEST_Task = new Task(1L, "Certificate on freecodeCamp", date, false, null);
		Task TEST_Task2 = new Task(2L, "Do 100 in a row Situps", date, false, null);
		List<Task> Task_List = List.of(TEST_Task, TEST_Task2);

		// Rules
		Mockito.when(this.mockedRepo.findAll()).thenReturn(Task_List);

		// Actions
		List<TaskDTO> TaskDTOList = Task_List.stream().map(this::mapToDTO).collect(Collectors.toList());

		// Assertions
		Assertions.assertThat(this.service.readAll()).isNotNull();
		Assertions.assertThat(this.service.readAll()).isEqualTo(TaskDTOList);

		// Assertions - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
	}

	@Test
	public void readOne() throws ParseException {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");

		Task TEST_TASK = new Task(1L, "Certificate on freecodeCamp", date, false, null);
		TaskDTO TEST_DTO = this.mockedMapper.map(TEST_TASK, TaskDTO.class);

		// Rules
		Mockito.when(this.mockedRepo.findById(TEST_TASK.getId())).thenReturn(Optional.of(TEST_TASK));

		// Actions
		TaskDTO result = this.service.readOne(1L);

		// Assertions
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		// Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	// Post Test

	@Test
	public void create() throws ParseException {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");

		Task TEST_TASK = new Task(1L, "Certificate on freecodeCamp", date, false, null);
		TaskDTO TEST_DTO = new TaskDTO(1L, "Certificate on freecodeCamp", date, false);

		// Rules
		Mockito.when(this.mockedRepo.save(Mockito.any(Task.class))).thenReturn(TEST_TASK);
		Mockito.when(this.mockedMapper.map(TEST_TASK, TaskDTO.class)).thenReturn(TEST_DTO);

		// Actions
		TaskDTO result = this.service.create(TEST_TASK);

		// Assertions
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(TEST_DTO);

		// Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(Task.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(TEST_TASK, TaskDTO.class);
	}

	// Put Test

	@Test
	public void update() throws ParseException {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");

		Task TEST_Task = new Task(1L, "Certificate on freecodeCamp", date, false, null);
		Task Updated_Task = new Task(null, "Finish CodingBat Java", date, false, null);
		Updated_Task.setId(1L);
		TaskDTO TEST_DTO = this.mockedMapper.map(Updated_Task, TaskDTO.class);

		// Rules
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(TEST_Task));
		Mockito.when(this.mockedMapper.map(Updated_Task, TaskDTO.class)).thenReturn(TEST_DTO);
		Mockito.when(this.mockedRepo.save(Updated_Task)).thenReturn(Updated_Task);

		// Actions
		TaskDTO result = this.service.update(1L, TEST_Task);

		// Assertion
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		// Assertion - Verification
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(Updated_Task, TaskDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(Task.class));
	}

	// Delete Test

	@Test
	public void delete() throws ParseException {

		// Resources
		SimpleDateFormat simply = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simply.parse("2021-10-20");
		
		Task TEST_Task = new Task(1L, "Certificate on freecodeCamp", date, false, null);

		// Rules
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(TEST_Task));
		Mockito.when(this.mockedRepo.existsById(1L)).thenReturn(false);

		// Actions
		boolean result = this.service.delete(1L);

		// Assertions
		Assertions.assertThat(result).isTrue();

		// Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(1L);

	}

}
