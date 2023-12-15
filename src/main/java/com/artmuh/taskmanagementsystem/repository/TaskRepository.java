package com.artmuh.taskmanagementsystem.repository;

import com.artmuh.taskmanagementsystem.entity.Task;
import com.artmuh.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, QuerydslPredicateExecutor<Task> {

    List<Task> findAllByAuthorId(User authorId);

    List<Task> findAllByExecutorName(String username);

}
