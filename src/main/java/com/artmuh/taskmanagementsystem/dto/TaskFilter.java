package com.artmuh.taskmanagementsystem.dto;

import com.artmuh.taskmanagementsystem.entity.PriorityTask;
import com.artmuh.taskmanagementsystem.entity.StatusTask;
import lombok.Builder;
import lombok.Value;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Value
@Builder
public class TaskFilter {
    String title;

    String description;

    @Enumerated(EnumType.STRING)
    StatusTask status;

    @Enumerated(EnumType.STRING)
    PriorityTask priority;

    String author;

    String executorUser;

    Integer countComments;
}
