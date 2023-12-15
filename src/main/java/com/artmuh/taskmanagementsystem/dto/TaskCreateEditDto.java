package com.artmuh.taskmanagementsystem.dto;

import com.artmuh.taskmanagementsystem.entity.PriorityTask;
import com.artmuh.taskmanagementsystem.entity.StatusTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class TaskCreateEditDto {

    public TaskCreateEditDto() {
    }

    @NotBlank
    @Size(min = 2, max = 32,message = "title must be in the range from 2 to 32 characters")
    String title;

    @NotBlank
    @Size(min = 2, max = 128,message = " description must be in the range from 2 to 128 characters")
    String description;

    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @Enumerated(EnumType.STRING)
   private PriorityTask priority;

    private UserReadDto author;

    String executorUser;

}
