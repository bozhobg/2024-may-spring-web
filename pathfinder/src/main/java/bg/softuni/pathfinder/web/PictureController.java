package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.UploadService;
import bg.softuni.pathfinder.util.CurrentUser;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;

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

    @GetMapping("/{routeId}/{filename}")
    public ResponseEntity<Resource> getPicture(
            @PathVariable("routeId") Long routeId,
            @PathVariable("filename") String filename
    ) {

/*
        ClassPathResource:
        All files placed in the /resources directory are placed in the application root ‘/’ at the build time.
        We can access them using the ClassPathResource class using the following approaches. (classpath:)

        This approach looks into the build folder () and doesn't update in real time!

        Path classPath = Path.of("/storage/pictures");
        Path subPath = Path.of(routeId.toString(), filename);
        Resource resource = new PathResource(classPath.resolve(subPath).toString());
*/

        Path absBasePath = Path.of("/home/bzh/SoftUni/14-15 Java Web 2024-May Redo/course-projects/pathfinder/src/main/resources/storage/pictures");
        Path subPath = Path.of(routeId.toString(), filename);

        Path absPath = absBasePath.resolve(subPath);

        Resource resource = new PathResource(absPath);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

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
