package com.qa.persistence.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
