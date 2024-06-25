package com.dictionaryapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper getModelMapper() {

        return new ModelMapper();
    }
}
