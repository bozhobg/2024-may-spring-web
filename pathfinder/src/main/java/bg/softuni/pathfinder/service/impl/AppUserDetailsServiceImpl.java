package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.entity.Role;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.user.AppUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

//    @Autowired -> only with @Service, this case as @Bean in WebSecConfig
    public AppUserDetailsServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));

        return mapToAppUserDetails(user);
    }

    private static AppUserDetails mapToAppUserDetails(User user) {

        AppUserDetails appUserDetails = new AppUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(AppUserDetailsServiceImpl::mapAuthority).toList(),
                user.getFullName(),
                user.getEmail()
        );

        return appUserDetails;
    }

//    TODO: On role entity implement GrantedAuthority
    private static SimpleGrantedAuthority mapAuthority(Role role) {

        return new SimpleGrantedAuthority(
                "ROLE_" + role.getName()
        );
    }
}
