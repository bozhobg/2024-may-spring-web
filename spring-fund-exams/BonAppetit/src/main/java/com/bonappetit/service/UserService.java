package com.bonappetit.service;

import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO data);

    @Transactional
    List<RecipeInfoDTO> getFavourites();
}
