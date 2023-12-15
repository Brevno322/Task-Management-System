package com.artmuh.taskmanagementsystem.controller;

import com.artmuh.taskmanagementsystem.dto.CommentDto;
import com.artmuh.taskmanagementsystem.dto.TaskReadDto;
import com.artmuh.taskmanagementsystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskReadDto saveComment(@RequestBody CommentDto comment){
        return commentService.save(comment);
    }
}
