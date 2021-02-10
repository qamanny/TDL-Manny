package com.qa.dto;

import java.util.List;

public class ProjectDTO {

	private Long id;

	private String projectName;
	private List<TaskDTO> taskList;

	public ProjectDTO() {
		super();
	}

	public ProjectDTO(Long id, String projectName, List<TaskDTO> taskList) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.taskList = taskList;
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

	public List<TaskDTO> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDTO> taskList) {
		this.taskList = taskList;
	}
	
	

}