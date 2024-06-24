package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public boolean isValid(StyleName name) {
        return this.styleRepository.existsByName(name);
    }
}
