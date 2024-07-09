package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.model.dto.RouteAddDTO;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private static final String ATTR_ROUTES = "routesData";
    private static final String ATTR_ADD = "routeData";
    private static final String ATTR_DETAILS = "routeDetails";

    private final RouteService routeService;
    private final CurrentUser currentUser;

    @Autowired
    public RouteController(
            RouteService routeService,
            CurrentUser currentUser
    ) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public String routes(
            Model model
    ) {
//        TODO: unauthenticated access?

        if (!model.containsAttribute(ATTR_ROUTES)) {
            model.addAttribute(ATTR_ROUTES, this.routeService.getRoutes());
        }


        return "routes";
    }

    @GetMapping("/add")
    public String getAddRoute(
            Model model
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

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
            RedirectAttributes rAttrs
    ) throws IOException {
//        TODO: different approach with multipart form, upload file in binding model
//        CASE: binding result should be immediately after the binding model. Multipart in between throws!

        if (!currentUser.isLogged()) return "redirect:/users/login";

        if (bindingResult.hasErrors() || gpxFile.isEmpty()) {

            rAttrs.addFlashAttribute("invalidFile", true);
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, ATTR_ADD);

            return "redirect:/routes/add";
        }

        Long routeId = this.routeService.add(bindingModel, gpxFile);

        if (routeId == null) {
            rAttrs.addFlashAttribute(ATTR_ADD, bindingModel);

            return "redirect:/routes/add";
        }

        return "redirect:/routes/" + routeId;
    }


    @GetMapping("/{id}")
    public String getDetails(
            @PathVariable Long id,
            Model model
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        RouteDetailsDTO routeData = this.routeService.getRouteDetails(id);

        if (routeData == null) return "redirect:/routes";

        model.addAttribute(ATTR_DETAILS, routeData);

//        TODO: provide/post/approve comments through rest, render using js, gpx map, video iframe, upload pic, download gpx

        return "route-details";
    }


}
