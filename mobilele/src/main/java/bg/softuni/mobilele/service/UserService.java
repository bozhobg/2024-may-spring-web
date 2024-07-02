package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.LoginDTO;
import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.entity.User;

public interface UserService {

    void register(RegisterDTO registerData);

    boolean login(LoginDTO loginData);

    boolean isUsernameUnique(String username);

    void logout();

    User getUserById(Long id);
}
