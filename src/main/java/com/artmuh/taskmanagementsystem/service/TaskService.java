package com.artmuh.taskmanagementsystem.service;

import com.artmuh.taskmanagementsystem.dto.*;
import com.artmuh.taskmanagementsystem.entity.Task;
import com.artmuh.taskmanagementsystem.entity.User;
import com.artmuh.taskmanagementsystem.mapper.TaskCreateEditDtoMapper;
import com.artmuh.taskmanagementsystem.mapper.TaskReadDtoMapper;
import com.artmuh.taskmanagementsystem.querydsl.QPredicates;
import com.artmuh.taskmanagementsystem.repository.TaskRepository;
import com.artmuh.taskmanagementsystem.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static com.artmuh.taskmanagementsystem.entity.QTask.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskReadDtoMapper taskReadDtoMapper;
    private final TaskCreateEditDtoMapper taskCreateEditDtoMapper;
    private final UserRepository userRepository;


    public Page<TaskReadDto> findAll(TaskFilter filter, Pageable pageable) {
        Predicate predicate = QPredicates.builder()
                .add(filter.getTitle(), task.title::startsWithIgnoreCase)
                .add(filter.getDescription(),task.description::contains)
                .add(filter.getExecutorUser(),task.executorName::startsWithIgnoreCase)
                .build();

        return taskRepository.findAll(predicate, pageable)
                .map(taskReadDtoMapper::map);
    }

    public Optional<TaskReadDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskReadDtoMapper::map);
    }

    @Transactional
    public TaskReadDto create(TaskCreateEditDto task) {
        return Optional.of(task)
                .map(taskCreateEditDtoMapper::map)
                .map(taskRepository::save)
                .map(taskReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<TaskReadDto> update(Long id, TaskCreateEditDto taskDto) {

        return taskRepository.findById(id)
                .map(task -> taskCreateEditDtoMapper.map(taskDto, task))
                .map(taskRepository::saveAndFlush)
                .map(taskReadDtoMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return taskRepository.findById(id)
                .map(entity -> {
                    taskRepository.delete(entity);
                    taskRepository.flush();
                    return true;
                })
                .orElse(false);
    }


    public List<TaskReadDto> findByAuthor(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        List<Task> allByAuthorId = taskRepository.findAllByAuthorId(user.orElseThrow());

        return allByAuthorId.stream().map(taskReadDtoMapper::map).toList();
    }

    public List<TaskReadDto> findByExecutor(String username) {

        List<Task> allByExecutorName = taskRepository.findAllByExecutorName(username);

        return allByExecutorName.stream().map(taskReadDtoMapper::map).toList();
    }




}
