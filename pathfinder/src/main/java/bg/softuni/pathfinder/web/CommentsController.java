package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.CommentContentPostDTO;
import bg.softuni.pathfinder.model.user.AppUserDetails;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentService;

    @Autowired
    public CommentsController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{routeId}")
    public String post(
            @PathVariable("routeId") Long routeId,
            @Valid CommentContentPostDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs,
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, "comment");

            return "redirect:/routes/" + routeId;
        }

        boolean success = this.commentService.add(bindingModel, routeId, userDetails.getId());

        // on error
        if (!success) {
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, "comment");

            return "redirect:/routes/" + routeId;
        }

        return "redirect:/routes/" + routeId;
    }

}
