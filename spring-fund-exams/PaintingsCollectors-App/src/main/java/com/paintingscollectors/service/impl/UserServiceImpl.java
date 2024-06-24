package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.CurrentUser;
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
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.httpSession = httpSession;
    }

    @Override
    public boolean loginUser(UserLoginDTO dto) {
        User user = this.userRepository.findUserByUsername(dto.getUsername()).orElse(null);

        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return false;
        }

        currentUser.login(user.getId(), user.getUsername());

        return true;
    }

    @Override
    public boolean registerUser(UserRegisterDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return false;
        }

        this.userRepository.save(this.mapToUser(dto));

        return true;
    }

    @Override
    public void logoutUser() {
        this.currentUser.logout();
        httpSession.invalidate();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return this.userRepository.existsByUsernameNot(username);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return this.userRepository.existsByEmailNot(email);
    }

    private User mapToUser(UserRegisterDTO dto) {
        return new User()
                .setUsername(dto.getUsername())
                .setEmail(dto.getEmail())
                .setPassword(this.passwordEncoder.encode(dto.getPassword()));
    }
}
