package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.CommentDto;
import com.artmuh.taskmanagementsystem.entity.Comment;
import com.artmuh.taskmanagementsystem.repository.TaskRepository;
import com.artmuh.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;


@Component
@RequiredArgsConstructor
public class CommentMapper implements Mapper<CommentDto, Comment>{

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;



    @Override
    public Comment map(CommentDto fromObjet) {
        return Comment.builder()
                .user(userRepository.findById(fromObjet.getUserId()).orElseThrow(()->new NotFoundException("not found user")))
                .task(taskRepository.findById(fromObjet.getTaskId()).orElseThrow(()->new NotFoundException("not found user")))
                .comment(fromObjet.getComment())
                .build();
    }

    @Override
    public Comment map(CommentDto fromObject, Comment toObject) {
        return Mapper.super.map(fromObject, toObject);
    }


}
