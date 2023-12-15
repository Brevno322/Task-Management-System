package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.TaskReadDto;
import com.artmuh.taskmanagementsystem.entity.Comment;
import com.artmuh.taskmanagementsystem.entity.Task;
import com.artmuh.taskmanagementsystem.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskReadDtoMapper implements Mapper<Task, TaskReadDto> {

    private final CommentRepository commentRepository;

    @Override
    public TaskReadDto map(Task fromObjet) {

        return copy(fromObjet);
    }

    private TaskReadDto copy(Task fromObjet) {
        return new TaskReadDto(
                fromObjet.getTitle(),
                fromObjet.getDescription(),
                fromObjet.getStatus(),
                fromObjet.getPriority(),
                fromObjet.getAuthorId().getUsername(),
                fromObjet.getExecutorName(),
                commentRepository.findByTaskId(fromObjet.getId()).stream()
                        .map(Comment::getComment)
                        .toList()
        );
    }


}
