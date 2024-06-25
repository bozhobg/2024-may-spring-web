package com.plannerapp.service;

import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO data);

    void logout();
}
