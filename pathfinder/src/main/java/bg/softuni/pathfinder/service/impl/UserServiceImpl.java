package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RoleRepository;
import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.model.enums.UserRole;
import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public UserProfileDTO getUserProfileData(Long userId) {
        User user = getById(userId);

        if (user == null) return null; //TODO: throw

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
