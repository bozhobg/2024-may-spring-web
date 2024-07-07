package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    boolean isUsernameUnique(String username);

    boolean isEmailUnique(String email);

    @Transactional
    boolean register(UserRegisterDTO dto);

    @Transactional
    boolean login(UserLoginDTO dto);

    void logout();

    UserProfileDTO getUserProfileData();
}
