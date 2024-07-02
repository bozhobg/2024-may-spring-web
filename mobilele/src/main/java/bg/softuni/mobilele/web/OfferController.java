package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import bg.softuni.mobilele.model.dto.OfferAddDTO;
import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final BrandService brandService;
    private final CurrentUser currentUser;

    @Autowired
    public OfferController(
            BrandService brandService,
            CurrentUser currentUser
    ) {
        this.brandService = brandService;
        this.currentUser = currentUser;
    }

    @GetMapping("/all")
    public String getAll() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";
//        TODO:

        return "offers";
    }

    @GetMapping("/offers/{id}")
    public String getOfferDetails(
            @PathVariable("id") Long offerId
    ) {
//        TODO:

        return "details";
    }
}
