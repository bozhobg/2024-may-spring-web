package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.UploadService;
import bg.softuni.pathfinder.util.CurrentUser;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/pictures")
public class PictureController {

    private final PictureService pictureService;
    private final UploadService uploadService;
    private final CurrentUser currentUser;

    @Autowired
    public PictureController(
            PictureService pictureService,
            UploadService uploadService,
            CurrentUser currentUser
    ) {
        this.pictureService = pictureService;
        this.uploadService = uploadService;
        this.currentUser = currentUser;
    }

//    TODO: should upload directly to cloud service, and store just links

//    @GetMapping("/{id}")
//    public String getImage() {}

    @PostMapping("/add")
    public String postPicture(
            @Valid PictureAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) throws IOException {

        if (!currentUser.isLogged()) return "redirect:/users/login";

//        TODO: on redirect picture for upload not part of model

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, "uploadPicture");

            return "redirect:/routes/" + bindingModel.getRouteId();
        }

        this.pictureService.add(bindingModel);

        return "redirect:/routes/" + bindingModel.getRouteId();
    }
}
