package com.qa.rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dto.TaskDTO;
import com.qa.persistence.domain.Task;
import com.qa.persistence.domain.repos.TaskRepo;

@Service
public class TaskService {
	
	private TaskRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private TaskDTO mapToDTO(Task model) {
		return this.mapper.map(model, TaskDTO.class);
	}
	
	// Get
	
	public List<TaskDTO> readAll() {
		List<Task> dbList = this.repo.findAll();
		List<TaskDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		return resultList;
	}
		
	public TaskDTO readOne(Long id) {
		
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
		
	// Post
		
	public TaskDTO create(Task task) {
		return this.mapToDTO(this.repo.save(task));
	}
		
		
	// Put
	
	public TaskDTO update(Long id, Task newDetails) {
		this.repo.findById(id).orElseThrow();
		
		newDetails.setId(id);
		TaskDTO result = mapToDTO(this.repo.save(newDetails));
		
		return result;
	}
		
		
	// Delete
		
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		
		return !this.repo.existsById(id);
	}
	

}
