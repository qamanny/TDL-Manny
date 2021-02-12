package com.qa.rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dto.ProjectDTO;
import com.qa.persistence.domain.Project;
import com.qa.persistence.domain.repos.ProjectRepo;

@Service
public class ProjectService {

	private ProjectRepo repo;
	private ModelMapper mapper;

	@Autowired
	public ProjectService(ProjectRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
  
	private ProjectDTO mapToDTO(Project model) {
		return this.mapper.map(model, ProjectDTO.class);
	}

	// Get

	public List<ProjectDTO> readAll() {
		List<Project> dbList = this.repo.findAll();
		List<ProjectDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return resultList;
	}

	public ProjectDTO readOne(Long id) {

		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// Post

	public ProjectDTO create(Project project) {
		return this.mapToDTO(this.repo.save(project));
	}

	// Put

	public ProjectDTO update(Long id, Project newDetails) {
		this.repo.findById(id).orElseThrow();

		newDetails.setId(id);
		ProjectDTO result = mapToDTO(this.repo.save(newDetails));

		return result;
	}

	// Delete

	public boolean delete(Long id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}

}
