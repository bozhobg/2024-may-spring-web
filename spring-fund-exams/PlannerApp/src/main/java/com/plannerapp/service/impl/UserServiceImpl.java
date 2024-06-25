package com.plannerapp.service.impl;

import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import com.plannerapp.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserSession userSession
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        if (this.userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail())) {
            return false;
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) return false;

        this.userRepository.save(
                new User()
                        .setUsername(data.getUsername())
                        .setEmail(data.getEmail())
                        .setPassword(this.passwordEncoder.encode(data.getPassword()))
        );

        return true;
    }

    @Override
    public boolean login(UserLoginDTO data) {
        User user = this.userRepository.findByUsername(data.getUsername())
                .filter(u -> this.passwordEncoder.matches(data.getPassword(), u.getPassword()))
                .orElse(null);

        if (user == null) return false;

        this.userSession.login(user.getId(), user.getUsername());

        return true;
    }

    @Override
    public void logout() {
        this.userSession.logout();
    }
}
