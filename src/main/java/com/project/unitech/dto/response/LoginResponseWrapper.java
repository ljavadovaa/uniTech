package com.project.unitech.dto.response;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginResponseWrapper<T> {
    private T data;
}
