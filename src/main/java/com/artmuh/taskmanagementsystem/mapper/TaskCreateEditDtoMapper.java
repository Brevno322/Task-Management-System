package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.TaskCreateEditDto;
import com.artmuh.taskmanagementsystem.entity.Task;
import com.artmuh.taskmanagementsystem.entity.User;
import com.artmuh.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskCreateEditDtoMapper implements Mapper<TaskCreateEditDto, Task> {

    private final UserRepository userRepository;

    @Override
    public Task map(TaskCreateEditDto fromObjet) {

        return Task.builder()
                .title(fromObjet.getTitle())
                .description(fromObjet.getDescription())
                .status(fromObjet.getStatus())
                .priority(fromObjet.getPriority())
                .authorId(getUserByUsername(fromObjet))
                .executorName(fromObjet.getExecutorUser())
                .build();

    }

    @Override
    public Task map(TaskCreateEditDto fromObject, Task toObject) {
        toObject.setTitle(fromObject.getTitle());
        toObject.setDescription(fromObject.getDescription());
        toObject.setStatus(fromObject.getStatus());
        toObject.setPriority(fromObject.getPriority());
        toObject.setAuthorId(getUserByUsername(fromObject));
        toObject.setExecutorName(fromObject.getExecutorUser());
        return toObject;
    }

    private User getUserByUsername(TaskCreateEditDto fromObjet) {
        return userRepository.findByUsername
                        (fromObjet.getAuthor().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException
                        ("User not found"));
    }
}
