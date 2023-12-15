package com.artmuh.taskmanagementsystem.service;

import com.artmuh.taskmanagementsystem.dto.CommentDto;
import com.artmuh.taskmanagementsystem.dto.TaskReadDto;
import com.artmuh.taskmanagementsystem.entity.Comment;
import com.artmuh.taskmanagementsystem.mapper.CommentMapper;
import com.artmuh.taskmanagementsystem.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;


    private final CommentMapper commentMapper;

    private final TaskService taskService;


    @Transactional
    public TaskReadDto save(CommentDto comment){
        Comment comm = commentMapper.map(comment);
        commentRepository.save(comm);

        return taskService.findById(comm.getTask().getId()).orElseThrow();
    }
}
