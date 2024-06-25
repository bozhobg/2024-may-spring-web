package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.LanguageEnum;

public class LanguageBasicDTO {

    private long id;

    private LanguageEnum name;

    public LanguageBasicDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LanguageEnum getName() {
        return name;
    }

    public void setName(LanguageEnum name) {
        this.name = name;
    }
}
