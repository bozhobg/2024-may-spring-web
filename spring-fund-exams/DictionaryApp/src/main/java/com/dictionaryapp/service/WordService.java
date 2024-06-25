package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.dto.WordViewDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface WordService {

    boolean addWord(WordAddDTO data);

    @Transactional
    Map<LanguageEnum, List<WordViewDTO>> getAllWordsAsDTOs();

    Long getWordsCount();

    void delete(Long id);

    void deleteAll();
}
