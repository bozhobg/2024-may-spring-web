package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordViewDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final WordService wordService;
    private final LanguageService languageService;
    private final UserSession userSession;

    @Autowired
    public HomeController(
            UserSession userSession,
            WordService wordService,
            LanguageService languageService
    ) {
        this.userSession = userSession;
        this.wordService = wordService;
        this.languageService = languageService;
    }

    @ModelAttribute(name = "wordsCount")
    public Long getWordsCount() {
        return this.wordService.getWordsCount();
    }

    @GetMapping("/")
    public String getIndex() {
        if (userSession.isLogged()) return "redirect:/home";

        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!userSession.isLogged()) return "redirect:/users/login";

        Map<LanguageEnum, List<WordViewDTO>> mapLangDTOs = this.wordService.getAllWordsAsDTOs();

        model.addAttribute("germanList", mapLangDTOs.get(LanguageEnum.GERMAN));
        model.addAttribute("spanishList", mapLangDTOs.get(LanguageEnum.SPANISH));
        model.addAttribute("frenchList", mapLangDTOs.get(LanguageEnum.FRENCH));
        model.addAttribute("italianList", mapLangDTOs.get(LanguageEnum.ITALIAN));

        return "home";
    }
}
