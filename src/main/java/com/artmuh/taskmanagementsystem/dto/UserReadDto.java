package com.artmuh.taskmanagementsystem.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class UserReadDto {

     public UserReadDto() {
     }

     @NotBlank
     @Email
     private String username;

     @NotBlank
     @Size(min = 2, max = 64,message = "must be in the range from 2 to 64 characters")
     private String name;


     List<TaskReadDto> createdTask;


}
// Role role;