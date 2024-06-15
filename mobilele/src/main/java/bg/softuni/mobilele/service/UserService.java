package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.LoginDTO;
import bg.softuni.mobilele.model.dto.RegisterDTO;

public interface UserService {
    void registerUser(RegisterDTO registerData);
    boolean loginUser(LoginDTO loginData);
}
