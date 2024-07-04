package bg.softuni.pathfinder.init;

import bg.softuni.pathfinder.data.RoleRepository;
import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.entity.Role;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        initAdditionalUsers();
    }

    private void initAdditionalUsers() {
        if (this.userRepository.count() > 4) return;

        List<Role> allRoles = this.roleRepository.findAll();
        Level[] allLevels = Level.values();
        List<User> newUsers = new ArrayList<>();

        for (Role role : allRoles) {
            String base = role.getName().name().toLowerCase();

            for (int i = 0; i < allLevels.length; i ++) {
                int seq = i + 1;
                Level lvl = allLevels[i];

                String username = base + seq;

                User newUser = new User()
                        .setUsername(username)
                        .setEmail(username + "@" + base)
                        .setFullName(username + " " + base + "ov")
                        .setPassword(passwordEncoder.encode(username))
                        .setLevel(lvl)
                        .setAge(seq);

                newUser.getRoles().add(role);

                newUsers.add(newUser);
            }

            this.userRepository.saveAll(newUsers);
        }


    }
}
