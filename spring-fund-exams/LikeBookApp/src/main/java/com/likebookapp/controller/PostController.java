package com.likebookapp.controller;

import com.likebookapp.model.dto.PostAddDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CurrentUser currentUser;

    @Autowired
    public PostController(
            PostService postService,
            CurrentUser currentUser
    ) {
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("postData")
    public PostAddDTO postData() {
        return new PostAddDTO();
    }

    @GetMapping("/add")
    public String getPostAdd() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        return "post-add";
    }

    @PostMapping("/add")
    public String postPostAdd(
            @Valid PostAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        if (bindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("postData", bindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.postData",
                    bindingResult
            );

            return "redirect:/posts/add";
        }

        this.postService.addPost(bindingModel);

        return "redirect:/home";
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable("id") Long postId) {
        if (!this.currentUser.isLogged()) {
            return "redirect:/users/login";
        }
//        TODO: fix > 1 like by user, hide like button!
        this.postService.likePost(currentUser.getId(), postId);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String deletePost(@PathVariable("id") Long postId) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        this.postService.deletePost(currentUser.getId(), postId);

        return "redirect:/home";
    }
}
