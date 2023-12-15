package com.artmuh.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CommentDto {

    public CommentDto() {
    }

    private Long userId;

    private Long taskId;

    @Size(min = 2, max = 256, message = " comment must be in the range from 2 to 256 characters")
    private String comment;

}
