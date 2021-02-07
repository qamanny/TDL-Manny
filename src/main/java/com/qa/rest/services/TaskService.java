package com.qa.rest.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.qa.dto.TaskDTO;
import com.qa.persistence.domain.Task;
import com.qa.persistence.domain.repos.TaskRepo;

@Service
public class TaskService {
	
	private TaskRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TaskService(TaskRepo repo) {
		super();
		this.repo = repo;
	}
	
	private TaskDTO map(Task model) {
		return this.mapper.map(repo, TaskDTO.class);
	}
	
	//Get
	
		@GetMapping
		
		//Post
		
		@PostMapping("/create")
		public TaskDTO create(Task task) {
			return this.map(this.repo.save(task));
		}
		
		
		//Put
		
		@PutMapping
		
		//Delete
		
		@DeleteMapping

	
	

}
