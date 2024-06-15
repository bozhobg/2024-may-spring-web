package bg.softuni.mobilele.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper() {

        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder getEncoder() {

        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
