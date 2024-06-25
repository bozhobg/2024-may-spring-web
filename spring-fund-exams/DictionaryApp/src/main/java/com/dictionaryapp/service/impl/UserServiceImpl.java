package com.dictionaryapp.service.impl;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository, UserSession userSession,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        if (this.userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail())) {

            return false;
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {

            return false;
        }

        User newUser = modelMapper.map(data, User.class);
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        this.userRepository.save(newUser);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO data) {
        Optional<User> byUsername = this.userRepository.findUserByUsername(data.getUsername());
//        TODO: how to chain Optional methods in this case

        if (byUsername.isEmpty()) {
            return false;
        }

        User user = byUsername.get();

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            return false;
        }

        this.userSession.login(user);
        return true;
    }

    @Override
    public void logout() {
        userSession.logout();
    }
}
