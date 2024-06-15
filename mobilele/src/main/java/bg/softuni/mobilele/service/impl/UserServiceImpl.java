package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.entity.User;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.RoleRepository;
import bg.softuni.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(RegisterDTO registerData) {

        if (userRepository.existsUserByUsername(registerData.getUsername()) ||
                roleRepository.existsById(registerData.getRoleId())) {

            throw new RuntimeException("Invalid registration parameters");
        }


        User entity = this.mapToUser(registerData);

        this.userRepository.save(entity);
    }

    private User mapToUser(RegisterDTO registerData) {

        return this.modelMapper.map(registerData, User.class)
                .setActive(true)
                .setCreated(Instant.now())
                .setModified(Instant.now());
    }
}
