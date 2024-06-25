package com.likebookapp.service;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;

public interface UserService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean register(UserRegisterDTO registerData);

    boolean validCredentials(UserLoginDTO loginData);

    void loginUser(String username);

    void logout();
}
