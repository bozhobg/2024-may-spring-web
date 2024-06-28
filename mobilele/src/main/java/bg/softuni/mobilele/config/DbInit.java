package bg.softuni.mobilele.config;

import bg.softuni.mobilele.model.entity.User;
import bg.softuni.mobilele.model.entity.UserRole;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.repository.RoleRepository;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DbInit implements CommandLineRunner {
//    TODO: move init logic in services

    private final static int USER_COUNT_BOUND = 8;
    private final static int ADMIN_COUNT_BOUND = 4;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initRoles();
        this.initUsers();
    }

    private void initRoles() {
//        TODO: change of enum values -> update db data, w/out duplication

        if (this.roleRepository.count() > 0) return;

        for (Role role : Role.values()) {
            this.roleRepository.save(new UserRole().setName(role));
        }
    }

    private void initUsers() {
        if (this.userRepository.count() > 0) return;

        String userBase = "user";
        String adminBase = "admin";

        Random random = new Random();
        int userCount = random.nextInt(USER_COUNT_BOUND);
        int adminCount = random.nextInt(ADMIN_COUNT_BOUND);

        List<User> users = this.generateUserListByRole(userBase, userCount, Role.USER);
        this.userRepository.saveAll(users);

        List<User> admins = this.generateUserListByRole(adminBase, adminCount, Role.ADMIN);
        this.userRepository.saveAll(admins);
    }

    private List<User> generateUserListByRole(String nameBase, int count, Role role) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i <= count; i++) {

            int seq = i + 1;

            users.add(new User()
                    .setUsername(nameBase + seq)
                    .setFirstName(nameBase + seq)
                    .setLastName(nameBase + "ov" + seq)
                    .setPassword(this.passwordEncoder.encode(nameBase + seq))
                    .setCreated(Instant.now())
                    .setRole(this.roleRepository.findByName(role))
                    .setActive(true));
        }

        return users;
    }
}
