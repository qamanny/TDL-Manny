package com.qa.dto;


public class ProjectDTO {
	
	private Long id;
	
	private String projectName;

	public ProjectDTO() {
		super();
	}

	public ProjectDTO(Long id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	

}
