package com.paintingscollectors.service;

import com.paintingscollectors.model.enums.StyleName;

public interface StyleService {
    boolean isValid(StyleName name);
}
