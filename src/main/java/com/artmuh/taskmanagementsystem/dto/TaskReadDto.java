package com.artmuh.taskmanagementsystem.dto;


import com.artmuh.taskmanagementsystem.entity.PriorityTask;
import com.artmuh.taskmanagementsystem.entity.StatusTask;
import lombok.Value;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Value
public class TaskReadDto {


    @NotBlank
    @Size(min = 2, max = 32,message = "title must be in the range from 2 to 32 characters")
     String title;

    @NotBlank
    @Size(min = 2, max = 128,message = " description must be in the range from 2 to 128 characters")
     String description;

    @Enumerated(EnumType.STRING)
    StatusTask status;

    @Enumerated(EnumType.STRING)
    PriorityTask priority;

    String author;

    String executorUser;

    List<String> comments;
}
