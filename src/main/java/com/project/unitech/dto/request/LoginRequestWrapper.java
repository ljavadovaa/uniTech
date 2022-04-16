package com.project.unitech.dto.request;

import lombok.Data;

@Data
public class LoginRequestWrapper<T> {
    private T data;
}
