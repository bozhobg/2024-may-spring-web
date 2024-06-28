package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.LoginDTO;
import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.entity.User;
import bg.softuni.mobilele.repository.RoleRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.utils.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            CurrentUser currentUser,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(RegisterDTO registerData) {

        registerData.setPassword(passwordEncoder.encode(registerData.getPassword()));
        User entity = this.mapToUser(registerData);

        this.userRepository.save(entity);
    }

    @Override
    public boolean login(LoginDTO loginData) {
        User user = this.userRepository.findUserByUsername(loginData.getUsername())
                .orElse(null);

        if (loginData.getPassword() == null ||
                user == null ||
                user.getPassword() == null ||
                !passwordEncoder.matches(loginData.getPassword(), user.getPassword())
        ) {
            return false;
        }

        this.currentUser.login(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName()
        );

        return true;
    }

    @Override
    public boolean isUsernameUnique(String username) {

        return this.userRepository.findUserByUsername(username).isEmpty();
    }

    @Override
    public void logout() {
        this.currentUser.logout();
    }


    private User mapToUser(RegisterDTO registerData) {

        return this.modelMapper.map(registerData, User.class)
                .setActive(true)
                .setCreated(Instant.now())
                .setModified(Instant.now());
    }
}
