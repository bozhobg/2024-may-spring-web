package com.likebookapp.service.impl;

import com.likebookapp.model.enums.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.service.MoodService;
import org.springframework.stereotype.Service;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;


    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public boolean isMoodValid(MoodEnum moodName) {

        return this.moodRepository.existsByName(moodName);
    }
}
