package com.qa.dto;

import java.util.Date;


public class TaskDTO {
	
	
	private Long id;
	
	private String taskName;
	
	private Date deadline;
	
	private Boolean completed;

	public TaskDTO() {
		super();
	}

	public TaskDTO(Long id, String taskName, Date deadline, Boolean completed) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.deadline = deadline;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	

}
