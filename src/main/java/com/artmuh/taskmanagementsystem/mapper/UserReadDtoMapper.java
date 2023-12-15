package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.UserReadDto;
import com.artmuh.taskmanagementsystem.entity.User;
import com.artmuh.taskmanagementsystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserReadDtoMapper implements Mapper<User, UserReadDto> {

    private final TaskRepository taskRepository;
    private final TaskReadDtoMapper taskReadDtoMapper;


    @Override
    public UserReadDto map(User object) {

        return new UserReadDto(
                object.getUsername(),
                object.getName(),
                taskRepository.findAllByAuthorId(object)
                        .stream()
                        .map(taskReadDtoMapper::map)
                        .toList()

        );
    }

}
