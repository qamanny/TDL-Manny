package com.qa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.ProjectDTO;
import com.qa.persistence.domain.Project;
import com.qa.rest.services.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private ProjectService service;

	public ProjectController(ProjectService service) {
		super();
		this.service = service;
	}

	// Get

	@GetMapping("/readAll")
	public ResponseEntity<List<ProjectDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());

	}

	@GetMapping("/read/{id}")
	public ResponseEntity<ProjectDTO> readProject(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));

	}

	// Post

	@PostMapping("/create")
	public ResponseEntity<ProjectDTO> create(@RequestBody Project project) {
		return new ResponseEntity<ProjectDTO>(this.service.create(project), HttpStatus.CREATED);	
	}

	// Put

	@PutMapping("/update/{id}")
	public ResponseEntity<ProjectDTO> update(@PathVariable("id") Long id, @RequestBody Project project) {
		return new ResponseEntity<ProjectDTO>(this.service.update(id, project), HttpStatus.ACCEPTED);
	}
	
	//Delete
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
