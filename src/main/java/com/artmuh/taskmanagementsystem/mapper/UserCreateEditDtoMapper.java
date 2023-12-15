package com.artmuh.taskmanagementsystem.mapper;

import com.artmuh.taskmanagementsystem.dto.UserCreateEditDto;
import com.artmuh.taskmanagementsystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditDtoMapper implements Mapper<UserCreateEditDto, User> {


    @Override
    public User map(UserCreateEditDto fromObjet) {
        User user = new User();
         copy(fromObjet, user);
         return user;
    }

    private void copy(UserCreateEditDto fromObjet, User user) {

        user.setUsername(fromObjet.getUsername());
        user.setName(fromObjet.getName());
        Optional.ofNullable(fromObjet.getRawPassword())
                .filter(StringUtils::hasText)
                .ifPresent(user::setPassword);
     //   user.setRole(fromObjet.getRole());
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
         copy(fromObject, toObject);
        return toObject;
    }
}
