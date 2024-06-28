package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import bg.softuni.mobilele.model.dto.OfferAddDTO;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @ModelAttribute("offerData")
    public OfferAddDTO offerData() {
        return new OfferAddDTO();
    }

    @ModelAttribute("brandsData")
    public List<BrandWithModelsBasicDTO> getBrandsAndModels() {

        return this.brandService.getBrandWithModelsDTOs();
    }

    @GetMapping("/add")
    public String getAdd() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "offer-add";
    }

    @GetMapping("/all")
    public String getAll() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "offers";
    }
}
