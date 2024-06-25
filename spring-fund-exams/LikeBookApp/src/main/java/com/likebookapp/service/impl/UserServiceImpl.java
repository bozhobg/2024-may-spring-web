package com.likebookapp.service.impl;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.UserService;
import com.likebookapp.util.CurrentUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final HttpSession httpSession;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CurrentUser currentUser, HttpSession httpSession
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.httpSession = httpSession;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        if (!data.getPassword().equals(data.getConfirmPassword())) return false;

        this.userRepository.save(new User()
                .setUsername(data.getUsername())
                .setEmail(data.getEmail())
                .setPassword(passwordEncoder.encode(data.getPassword()))
        );

        return true;
    }

    @Override
    public boolean validCredentials(UserLoginDTO loginData) {

        return this.userRepository.findByUsername(loginData.getUsername())
                .map(u -> passwordEncoder.matches(loginData.getPassword(), u.getPassword()))
                .orElse(false);
    }

    @Override
    public void loginUser(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow();
        this.currentUser.login(user.getId(), user.getUsername());
    }

    @Override
    public void logout() {
        this.currentUser.logout();
        httpSession.invalidate();
    }
}
