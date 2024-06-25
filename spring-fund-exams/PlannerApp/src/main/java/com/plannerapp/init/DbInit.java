package com.plannerapp.init;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final PriorityRepository priorityRepository;

    @Autowired
    public DbInit(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.priorityRepository.count() > 0) return;

        for (PriorityName name : PriorityName.values()) {

            this.priorityRepository.save(
                    new Priority()
                            .setName(name)
                            .setDescription(name.getDescription()));
        }
    }
}
