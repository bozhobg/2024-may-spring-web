package bg.softuni.pathfinder.config;

import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.service.impl.AppUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return new AppUserDetailsServiceImpl(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

//        TODO: still have access to all endpoints FIX: disable remember me

        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/", "/about", "/users/login", "/users/register", "/routes",
//                                        TODO: how to limit to certain file types, provide small amount
                                                "/pictures/*/*"
                                        ).permitAll()
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(
                        login ->
                                login
                                        .loginPage("/users/login")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
//                                TODO: doesn't redirect to previous unauthorized page visit
                                        .defaultSuccessUrl("/home")
//                                .failureForwardUrl("/users/login") -> stack overflow (almost), app hangs
                                        .failureUrl("/users/login")
                )
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                )
                .build();
    }
}
