package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.LanguageBasicDTO;

import java.util.List;

public interface LanguageService {
    List<LanguageBasicDTO> getLanguagesAsDTOs();
}
