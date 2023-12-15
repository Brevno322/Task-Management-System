package com.artmuh.taskmanagementsystem.service;

import com.artmuh.taskmanagementsystem.dto.UserCreateEditDto;
import com.artmuh.taskmanagementsystem.dto.UserFilter;
import com.artmuh.taskmanagementsystem.dto.UserReadDto;
import com.artmuh.taskmanagementsystem.mapper.UserCreateEditDtoMapper;
import com.artmuh.taskmanagementsystem.mapper.UserReadDtoMapper;
import com.artmuh.taskmanagementsystem.querydsl.QPredicates;
import com.artmuh.taskmanagementsystem.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static com.artmuh.taskmanagementsystem.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final UserReadDtoMapper userReadDtoMapper;
    private final UserCreateEditDtoMapper userCreateEditDtoMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicates.builder()
                .add(filter.getUsername(), user.username::containsIgnoreCase)
                .add(filter.getName(), user.name::containsIgnoreCase)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadDtoMapper::map);
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).
                map(userReadDtoMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(userDto.getRawPassword());
        userDto.setRawPassword(password);

        return Optional.of(userDto)
                .map(userCreateEditDtoMapper::map)
                .map(userRepository::save)
                .map(userReadDtoMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(userDto.getRawPassword());
        userDto.setRawPassword(password);

        return userRepository.findById(id)
                .map(entity -> userCreateEditDtoMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadDtoMapper::map);

    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {

                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }


}
