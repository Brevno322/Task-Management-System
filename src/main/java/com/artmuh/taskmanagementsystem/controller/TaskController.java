package com.artmuh.taskmanagementsystem.controller;

import com.artmuh.taskmanagementsystem.dto.*;
import com.artmuh.taskmanagementsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {


    private final TaskService taskService;

    @GetMapping
    public Page<TaskReadDto> findAll(TaskFilter taskFilter, Pageable pageable) {
        return taskService.findAll(taskFilter,pageable);
    }

    @GetMapping("/{id}")
    public TaskReadDto findById(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public TaskReadDto create(@RequestBody @Valid TaskCreateEditDto taskCreateEditDto) {
        return taskService.create(taskCreateEditDto);

    }

    @PutMapping("/{id}")
    public TaskReadDto update(@PathVariable Long id, @RequestBody @Valid TaskCreateEditDto taskCreateEditDto) {
        return taskService.update(id, taskCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return taskService.delete(id) ?
                noContent().build() :
                notFound().build();
    }

    @GetMapping("/findByAuthor/{username}")
    public List<TaskReadDto> findByAuthor(@PathVariable String username){
        return taskService.findByAuthor(username);
    }

    @GetMapping("/findByExecutor/{executor}")
    public List<TaskReadDto> findByExecutor(@PathVariable String executor){
        return taskService.findByExecutor(executor);
    }

}
