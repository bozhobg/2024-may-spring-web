package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.RegisterDTO;

public interface UserService {
    void registerUser(RegisterDTO registerData);
}
