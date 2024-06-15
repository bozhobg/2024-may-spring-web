package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.constants.ErrorMessages;
import bg.softuni.mobilele.model.dto.LoginDTO;
import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.entity.User;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.RoleRepository;
import bg.softuni.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(RegisterDTO registerData) {

        if (userRepository.existsUserByUsername(registerData.getUsername()) ||
                !roleRepository.existsById(registerData.getRoleId())) {

            throw new RuntimeException("Invalid registration parameters");
        }

        registerData.setPassword(passwordEncoder.encode(registerData.getPassword()));
        User entity = this.mapToUser(registerData);

        this.userRepository.save(entity);
    }

    @Override
    public boolean loginUser(LoginDTO loginData) {
        User user = this.userRepository.findUserByUsername(loginData.getUsername())
                .orElse(null);

        if (loginData.getPassword() == null || user == null || user.getPassword() == null) {
            return false;
        }

        boolean isPasswordValid = passwordEncoder.matches(loginData.getPassword(), user.getPassword());

//        TODO: implement current user logic

        return isPasswordValid;
    }

    private User mapToUser(RegisterDTO registerData) {

        return this.modelMapper.map(registerData, User.class)
                .setActive(true)
                .setCreated(Instant.now())
                .setModified(Instant.now());
    }
}
