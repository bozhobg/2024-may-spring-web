package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.CommentContentPostDTO;
import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.model.dto.RouteAddDTO;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.model.user.AppUserDetails;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.exception.ObjectNotFoundException;
import bg.softuni.pathfinder.service.exception.UploadFileException;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private static final String ATTR_ROUTES = "routesData";
    private static final String ATTR_ADD = "routeData";
    private static final String ATTR_DETAILS = "routeDetails";
    private static final String ATTR_UPLOAD = "uploadPicture";

    private final RouteService routeService;

    @Autowired
    public RouteController(
            RouteService routeService
    ) {
        this.routeService = routeService;
    }

    @ModelAttribute("comment")
    public CommentContentPostDTO comment() {
        return new CommentContentPostDTO();
    }

    @GetMapping
    public String routes(
            Model model
    ) {
        if (!model.containsAttribute(ATTR_ROUTES)) {
            model.addAttribute(ATTR_ROUTES, this.routeService.getRoutes());
        }

        return "routes";
    }

    @GetMapping("/add")
    public String getAddRoute(
            Model model
    ) {
//        TODO:
        model.addAttribute("levels", Level.values());
        model.addAttribute("cats", CategoryType.values());

        if (!model.containsAttribute(ATTR_ADD)) {
            model.addAttribute(ATTR_ADD, new RouteAddDTO());
        }

//        CASE: in template ${attr} ? ... : ... -> ok, encapsulated expr ${attr ? ... : ...} -> throws
//        if (!model.containsAttribute("invalidFile")) {
//            model.addAttribute("invalidFile", false);
//        }

        return "add-route";
    }

    @PostMapping(value = "/add")
    public String postAddRoute(
            @Valid RouteAddDTO bindingModel,
            BindingResult bindingResult,
            @RequestPart("gpxCoordinates") MultipartFile gpxFile,
            RedirectAttributes rAttrs,
            @AuthenticationPrincipal AppUserDetails userDetails
    ) throws IOException {
//        TODO: different approach with multipart form, upload file in binding model
//        CASE: binding result should be immediately after the binding model. Multipart in between throws!

        if (bindingResult.hasErrors() || gpxFile.isEmpty()) {

            rAttrs.addFlashAttribute("invalidFile", true);
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, ATTR_ADD);

            return "redirect:/routes/add";
        }

        try {
            Long routeId = this.routeService.add(bindingModel, gpxFile, userDetails.getId());
            return "redirect:/routes/" + routeId;
        } catch (IllegalArgumentException exc) {

            return "redirect:/routes/add";
        }

//        catch multiple exceptions catch (IllegalArgumentException | UploadFileException exc) -> must not contain ancestor and subclass
    }


    @GetMapping("/{id}")
    public String getDetails(
            @PathVariable Long id,
            Model model
    ) {
//        TODO: render map and youtube iframe

        RouteDetailsDTO routeData = this.routeService.getRouteDetails(id);

        model.addAttribute(ATTR_DETAILS, routeData);

        if (!model.containsAttribute(ATTR_UPLOAD)) {
            model.addAttribute(ATTR_UPLOAD, new PictureAddDTO());
        }

        return "route-details";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ObjectNotFoundException.class})
    public ModelAndView handleRouteNotFound(ObjectNotFoundException onfe) {
        ModelAndView mav = new ModelAndView("error-template");
        mav.addObject("errorMessage", onfe.getMessage());
        mav.addObject("forId", onfe.getId());

        return mav;
    }

}
