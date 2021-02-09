package com.qa.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String taskName;
	
	@Column
	private Date deadline;
	
	@Column
	private Boolean completed;
	
	@ManyToOne
	private Project myProject;

	public Task() {
		super();
	}

	public Task(Long id, String taskName, Date deadline, Boolean completed, Project myProject) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.deadline = deadline;
		this.completed = completed;
		this.myProject = myProject;
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

	public Project getMyProject() {
		return myProject;
	}

	public void setMyProject(Project myProject) {
		this.myProject = myProject;
	}

	

}
