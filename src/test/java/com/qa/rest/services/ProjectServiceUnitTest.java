package com.qa.rest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.dto.ProjectDTO;
import com.qa.persistence.domain.Project;
import com.qa.persistence.domain.repos.ProjectRepo;

@SpringBootTest
public class ProjectServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private ProjectRepo mockedRepo;

	@Autowired
	private ProjectService service;

	private ProjectDTO mapToDTO(Project model) {
		return this.mockedMapper.map(model, ProjectDTO.class);
	}

	// Get Test

	@Test
	public void readAll() {

		// Resources
		Project TEST_Project = new Project(1L, "Learning Javascript", null);
		Project TEST_Project2 = new Project(2L, "Six Pack", null);
		List<Project> Project_List = List.of(TEST_Project, TEST_Project2);

		// Rules
		Mockito.when(this.mockedRepo.findAll()).thenReturn(Project_List);

		// Actions
		List<ProjectDTO> ProjectDTOList = Project_List.stream().map(this::mapToDTO).collect(Collectors.toList());

		// Assertions
		Assertions.assertThat(this.service.readAll()).isNotNull();
		Assertions.assertThat(this.service.readAll()).isEqualTo(ProjectDTOList);

		// Assertions - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
	}

	@Test
	public void readOne() {

		// Resources
		Project TEST_Project = new Project(1L, "Learning Javascript", null);
		ProjectDTO TEST_DTO = this.mockedMapper.map(TEST_Project, ProjectDTO.class);

		// Rules
		Mockito.when(this.mockedRepo.findById(TEST_Project.getId())).thenReturn(Optional.of(TEST_Project));

		// Actions
		ProjectDTO result = this.service.readOne(1L);

		// Assertions
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		// Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);

	}

	// Post Test

	@Test
	public void create() {

		// Resources
		Project TEST_Project = new Project(1L, "Learning Javascript", null);
		ProjectDTO TEST_DTO = new ProjectDTO(1L, "Learning Javascript", null);

		// Rules
		Mockito.when(this.mockedRepo.save(Mockito.any(Project.class))).thenReturn(TEST_Project);
		Mockito.when(this.mockedMapper.map(TEST_Project, ProjectDTO.class)).thenReturn(TEST_DTO);
		// Actions
		ProjectDTO result = this.service.create(TEST_Project);

		// Assertions
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(TEST_DTO);

		// Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(Project.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(TEST_Project, ProjectDTO.class);
	}

	// Put Test

	@Test
	public void update() {

		// Resources
		Project TEST_Project = new Project(1L, "Learning Javascript", null);
		Project Updated_Project = new Project(null, "Learning Python", null);
		Updated_Project.setId(1L);
		ProjectDTO TEST_DTO = this.mockedMapper.map(Updated_Project, ProjectDTO.class);

		// Rules
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(TEST_Project));
		Mockito.when(this.mockedMapper.map(Updated_Project, ProjectDTO.class)).
		thenReturn(TEST_DTO);
		Mockito.when(this.mockedRepo.save(Updated_Project)).thenReturn(Updated_Project);

		// Actions
		ProjectDTO result = this.service.update(1L, TEST_Project);

		// Assertion
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		// Assertion - Verification
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(Updated_Project, ProjectDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(Project.class));
	}  
  
	// Delete Test

	@Test
	public void delete() {

		// Resources
		Project TEST_Project = new Project(1L, "Learning Javascript", null);

		// Rules
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(TEST_Project));
		Mockito.when(this.mockedRepo.existsById(1L)).thenReturn(false);

		// Actions
		boolean result = this.service.delete(1L);

		// Assertions
		Assertions.assertThat(result).isTrue();

		// Assertion - Verification
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(1L);

	}

}
