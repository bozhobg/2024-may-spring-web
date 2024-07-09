package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RoleRepository;
import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.model.entity.Role;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.model.enums.UserRole;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
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

        Role admin = this.roleRepository.findByName(UserRole.ADMIN);

        this.currentUser.login(
                user.getId(),
                user.getFullName(),
                admin != null && user.getRoles().contains(admin)
        );

        return true;
    }

    @Override
    public void logout() {
        this.currentUser.logout();
    }

    @Override
    public UserProfileDTO getUserProfileData() {
        User user = this.userRepository.findById(this.currentUser.getId()).orElse(null);

        if (user == null) return null;

        return mapToProfileData(user);
    }

    private User mapFromRegisterDTO(UserRegisterDTO dto) {
        User user = modelMapper.map(dto, User.class)
                .setLevel(Level.BEGINNER)
                .setPassword(passwordEncoder.encode(dto.getPassword()));

        user.getRoles().add(this.roleRepository.findByName(UserRole.USER));

        return user;
    }

    private UserProfileDTO mapToProfileData(User user) {
        return modelMapper.map(user, UserProfileDTO.class);
    }
}
