package com.dictionaryapp.service.impl;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.dto.WordViewDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final UserSession userSession;
    private final ModelMapper modelMapper;

    @Autowired
    public WordServiceImpl(
            WordRepository wordRepository,
            UserRepository userRepository,
            LanguageRepository languageRepository,
            UserSession userSession,
            ModelMapper modelMapper
    ) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addWord(WordAddDTO data) {
        Word newWord = this.modelMapper.map(data, Word.class);
//        TODO: ModelMapper sets languageId as word id?(to verify) how to fix, apart from resetting to null?
        newWord.setId(null);
        Optional<User> userById = this.userRepository.findById(userSession.getId());
        Optional<Language> languageById = this.languageRepository.findById(data.getLanguageId());

        if (userById.isEmpty() || languageById.isEmpty()) return false;

        newWord.setAddedBy(userById.get());
        newWord.setLanguage(languageById.get());

        this.wordRepository.save(newWord);

        return true;
    }

    @Override
    @Transactional
    public Map<LanguageEnum, List<WordViewDTO>> getAllWordsAsDTOs() {
        Map<LanguageEnum, List<WordViewDTO>> mapLangWords = new HashMap<>();

        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            mapLangWords.put(languageEnum, new ArrayList<>());
        }

        for (Word word : this.wordRepository.findAll()) {
            WordViewDTO dto = modelMapper.map(word, WordViewDTO.class);
//            RESOLVED: doesn't map username => wrong field naming addByUsername, instead of addedByUsername
            LanguageEnum langName = word.getLanguage().getName();
            mapLangWords.get(langName).add(dto);
        }

        return mapLangWords;
    }

    @Override
    public Long getWordsCount() {
        return this.wordRepository.count();
    }

    @Override
    public void delete(Long id) {
        this.wordRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.wordRepository.deleteAll();;
    }


}
