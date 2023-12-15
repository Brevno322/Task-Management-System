package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.TaskReadDto;
import com.artmuh.taskmanagementsystem.entity.Task;
import com.artmuh.taskmanagementsystem.entity.User;
import com.artmuh.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskReadToTask implements Mapper<TaskReadDto, Task>{
    private final UserRepository userRepository;
    @Override
    public Task map(TaskReadDto fromObjet) {
        return Task.builder()
                .title(fromObjet.getTitle())
                .description(fromObjet.getDescription())
                .status(fromObjet.getStatus())
                .priority(fromObjet.getPriority())
                .authorId(getUserByUsername(fromObjet))
                .executorName(fromObjet.getExecutorUser())
                .build();
    }
    private User getUserByUsername(TaskReadDto fromObjet) {
        return userRepository.findByUsername
                        (fromObjet.getAuthor())
                .orElseThrow(() -> new UsernameNotFoundException
                        ("User not found"));
    }


    @Override
    public Task map(TaskReadDto fromObject, Task toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
