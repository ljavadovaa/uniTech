package com.project.unitech.dto;

import com.project.unitech.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private User user;
    private String pin;

}
