package com.artmuh.taskmanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {
    String username;
    String name;
}
