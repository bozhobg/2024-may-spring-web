package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.BrandWithModelsInfoDTO;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;
    private final CurrentUser currentUser;

    @Autowired
    public BrandController(
            BrandService brandService,
            CurrentUser currentUser
    ) {
        this.brandService = brandService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("brandsData")
    public List<BrandWithModelsInfoDTO> brandsData() {

        return this.brandService.getBrandsWithModelsInfoDTOs();
    }

    @GetMapping("/all")
    public String getAll() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "brands";
    }
}
