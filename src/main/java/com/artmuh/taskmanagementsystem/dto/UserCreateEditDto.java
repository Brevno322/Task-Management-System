package com.artmuh.taskmanagementsystem.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateEditDto {

    @NotBlank
    @Email
    private String username;


    @NotBlank
    @Size(min = 2, max = 64,message = "must be in the range from 2 to 64 characters")
    private String name;


    @NotBlank
    @Size(min = 6, max = 64, message = "the password must contain from 6 to 64 characters")
    private String rawPassword;

}
