package bg.softuni.mobilele.config;

import bg.softuni.mobilele.model.entity.UserRole;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DbInit (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initRoles();
    }

    private void initRoles() {
//        TODO: change of enum values -> update db data, w/out duplication

        if (this.roleRepository.count() > 0) return;

        for (Role role : Role.values()) {
            this.roleRepository.save(new UserRole().setName(role));
        }
    }
}
