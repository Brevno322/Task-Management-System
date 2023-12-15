package com.artmuh.taskmanagementsystem.controller;

import com.artmuh.taskmanagementsystem.dto.UserCreateEditDto;
import com.artmuh.taskmanagementsystem.dto.UserFilter;
import com.artmuh.taskmanagementsystem.dto.UserReadDto;
import com.artmuh.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserReadDto> findAll(UserFilter userFilter,Pageable pageable) {
        return userService.findAll(userFilter,pageable);
    }


    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public UserReadDto create(@RequestBody @Valid UserCreateEditDto user) {

        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable Long id, @RequestBody @Valid UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userService.delete(id) ?
                noContent().build() :
                notFound().build();
    }


}
