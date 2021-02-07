package com.qa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rest.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	private TaskService service;

	public TaskController(TaskService service) {
		super();
		this.service = service;
	}
	
	//Get
	
	@GetMapping
	
	//Post
	
	@PostMapping
	
	//Put
	
	@PutMapping
	
	//Delete
	
	@DeleteMapping

}
