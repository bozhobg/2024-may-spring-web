package com.bonappetit.service.impl;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserSession userSession,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        if (this.userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail())) {
            return false;
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        this.userRepository.save(
                new User().setUsername(data.getUsername())
                        .setEmail(data.getEmail())
                        .setPassword(passwordEncoder.encode(data.getPassword()))
        );

        return true;
    }

    @Override
    public boolean login(UserLoginDTO data) {
        User user = this.userRepository.findByUsername(data.getUsername())
                .filter(entity -> passwordEncoder.matches(data.getPassword(), entity.getPassword()))
                .orElse(null);

        if (user == null) return false;

        userSession.login(user.getId(), user.getUsername());

        return true;
    }

    @Override
    public List<RecipeInfoDTO> getFavourites() {
        Optional<User> userById = this.userRepository.findById(this.userSession.getId());

        return userById.map(user -> user.getFavouriteRecipes()
                .stream()
                .map(r -> new RecipeInfoDTO()
                        .setId(r.getId())
                        .setName(r.getName())
                        .setIngredients(r.getIngredients()))
                .toList())
                .orElseGet(ArrayList::new);

    }
}
