package com.likebookapp.service;

import com.likebookapp.model.enums.MoodEnum;

public interface MoodService {
    boolean isMoodValid(MoodEnum moodName);
}
