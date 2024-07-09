package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    User getById(Long id);

    boolean isUsernameUnique(String username);

    boolean isEmailUnique(String email);

    @Transactional
    boolean register(UserRegisterDTO dto);

    @Transactional
    boolean login(UserLoginDTO dto);

    void logout();

    UserProfileDTO getUserProfileData();
}
