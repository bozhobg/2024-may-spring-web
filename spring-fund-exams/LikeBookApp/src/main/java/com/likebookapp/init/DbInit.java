package com.likebookapp.init;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final MoodRepository moodRepository;

    @Autowired
    public DbInit(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.moodRepository.count() > 0) return;

        for (MoodEnum value : MoodEnum.values()) {
            this.moodRepository.save(new Mood()
                    .setName(value)
                    .setDescription(value.getDescription())
            );
        }
    }
}
