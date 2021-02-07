package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.TaskDTO;
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
	
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
		
	}
	
	
	@GetMapping("/read/{id}")
	
	//Post
	
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody Task task) {
		return new ResponseEntity<TaskDTO>(this.service.create(task), HttpStatus.CREATED);
	}
	
	
	//Put
	
	@PutMapping
	
	//Delete
	
	@DeleteMapping

}
