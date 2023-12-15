package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.UserReadDto;
import com.artmuh.taskmanagementsystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadDtoToUser implements Mapper<UserReadDto, User>{


    @Override
    public User map(UserReadDto fromObjet) {
        User user = new User();
        copy(fromObjet, user);
        return user;
    }

    private void copy(UserReadDto fromObjet, User user) {
        user.setUsername(fromObjet.getUsername());
        user.setName(fromObjet.getName());
    }

    @Override
    public User map(UserReadDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


}
