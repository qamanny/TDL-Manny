package com.qa.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.persistence.domain.Task;
import com.qa.persistence.domain.repos.TaskRepo;

@Service
public class TaskService {
	
	private TaskRepo repo;

	@Autowired
	public TaskService(TaskRepo repo) {
		super();
		this.repo = repo;
	}
	
	//Get
	
		@GetMapping
		
		//Post
		
		@PostMapping("/create")
		public Task create(Task task) {
			return this.repo.save(task);
		}
		
		
		//Put
		
		@PutMapping
		
		//Delete
		
		@DeleteMapping

	
	

}
