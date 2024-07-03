package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RoleRepository;
import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.model.enums.UserRole;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            CurrentUser currentUser,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean register(UserRegisterDTO bindingModel) {
        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            return false;
        }

        this.userRepository.save(mapFromRegisterDTO(bindingModel));

        return true;
    }

    @Override
    public boolean login(UserLoginDTO dto) {
        User user = this.userRepository.findByUsername(dto.getUsername()).orElse(null);

        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return false;
        }

        this.currentUser.login(user.getId(), user.getFullName());

        return true;
    }

    private User mapFromRegisterDTO(UserRegisterDTO dto) {
        User user = modelMapper.map(dto, User.class)
                .setLevel(Level.BEGINNER)
                .setPassword(passwordEncoder.encode(dto.getPassword()));

        user.getRoles().add(this.roleRepository.findByName(UserRole.USER));

        return user;
    }
}
