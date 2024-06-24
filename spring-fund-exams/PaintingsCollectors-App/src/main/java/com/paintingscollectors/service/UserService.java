package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;

public interface UserService {

    boolean loginUser(UserLoginDTO dto);

    boolean registerUser(UserRegisterDTO dto);

    void logoutUser();

    boolean isUsernameUnique(String username);

    boolean isEmailUnique(String email);
}
