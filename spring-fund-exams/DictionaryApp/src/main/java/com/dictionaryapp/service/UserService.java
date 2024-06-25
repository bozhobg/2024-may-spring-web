package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO data);

    void logout();
}
