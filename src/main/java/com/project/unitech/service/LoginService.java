package com.project.unitech.service;

import com.project.unitech.dto.request.LoginRequestWrapper;
import com.project.unitech.dto.response.LoginResponseWrapper;

public interface LoginService {

    LoginResponseWrapper doLogin(LoginRequestWrapper loginRequestWrapper);

}
