package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.LanguageBasicDTO;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.service.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LanguageServiceImpl(
            LanguageRepository languageRepository,
            ModelMapper modelMapper
    ) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LanguageBasicDTO> getLanguagesAsDTOs() {
        return this.languageRepository.findAll().stream()
                .map(e -> modelMapper.map(e, LanguageBasicDTO.class))
                .toList();
    }
}
