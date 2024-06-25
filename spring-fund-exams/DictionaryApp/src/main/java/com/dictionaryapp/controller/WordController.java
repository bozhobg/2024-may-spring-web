package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.constants.ErrorMessages;
import com.dictionaryapp.model.dto.LanguageBasicDTO;
import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/words")
public class WordController {

    private final UserSession userSession;
    private final WordService wordService;
    private final LanguageService languageService;

    @Autowired
    public WordController(
            UserSession userSession,
            WordService wordService,
            LanguageService languageService
    ) {
        this.userSession = userSession;
        this.wordService = wordService;
        this.languageService = languageService;
    }

    @ModelAttribute(name = "languages")
    public List<LanguageBasicDTO> languages() {
        return this.languageService.getLanguagesAsDTOs();
    }

    @ModelAttribute(name = "wordData")
    public WordAddDTO wordAddDTO() {
        return new WordAddDTO();
    }

    @GetMapping("/add")
    public String getAdd() {
        if (!userSession.isLogged()) return "redirect:/users/login";

        return "word-add";
    }

    @PostMapping("/add")
    public String postAdd(
            @Valid WordAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!userSession.isLogged()) return "redirect:/users/login";

//        TODO: proper error messages

        if (bindingResult.hasErrors()) {
            this.setRedirectAttributes(bindingModel, bindingResult, rAttrs);

            return "redirect:/words/add";
        }

        boolean success = this.wordService.addWord(bindingModel);

        if (!success) {
            bindingResult.addError(new FieldError("wordData", "languageId", ErrorMessages.INVALID_LANGUAGE));
            this.setRedirectAttributes(bindingModel, bindingResult, rAttrs);

            return "redirect:/words/add";
        }

        return "redirect:/home";
    }

    private void setRedirectAttributes(
            WordAddDTO data,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        rAttrs.addFlashAttribute("wordData", data);
        rAttrs.addFlashAttribute(
                "org.springframework.validation.BindingResult.wordData",
                bindingResult
        );
    }


    @DeleteMapping("/delete/{id}")
    public String deleteWord(@PathVariable Long id) {
        if (!userSession.isLogged()) return "redirect:/users/login";

        this.wordService.delete(id);

        return "redirect:/home";
    }

    @DeleteMapping("/delete/all")
    public String deleteAllWords() {
        if (!userSession.isLogged()) return "redirect:/users/login";

        this.wordService.deleteAll();

        return "redirect:/home";
    }
}
