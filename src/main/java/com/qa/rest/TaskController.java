package com.qa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.Task;
import com.qa.rest.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	private TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		super();
		this.service = service;
	}
	
	//Get
	
	@GetMapping
	
	//Post
	
	@PostMapping("/create")
	public Task create(@RequestBody Task task) {
		return this.service.create(task);
	}
	
	
	//Put
	
	@PutMapping
	
	//Delete
	
	@DeleteMapping

}
