package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import bg.softuni.mobilele.model.dto.OfferAddDTO;
import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.utils.CurrentUser;
import bg.softuni.mobilele.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/offers/add")
public class OfferAddController {

    private static final String ATTR_NAME = "offerData";

    private final BrandService brandService;
    private final OfferService offerService;
    private final CurrentUser currentUser;

    @Autowired
    public OfferAddController(
            BrandService brandService,
            OfferService offerService,
            CurrentUser currentUser
    ) {
        this.brandService = brandService;
        this.offerService = offerService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(ATTR_NAME)
    public OfferAddDTO offerData() {
        return new OfferAddDTO();
    }

    @ModelAttribute("brandsData")
    public List<BrandWithModelsBasicDTO> getBrandsAndModels() {
        return this.brandService.getBrandWithModelsDTOs();
    }

    @ModelAttribute("engineTypes")
    public Engine[] getEngineTypes() {
        return Engine.values();
    }

    @ModelAttribute("transmissionTypes")
    public Transmission[] getTransmissionTypes() {
        return Transmission.values();
    }

//    TODO: add rest of other needed model attrs

    @GetMapping
    public String getAdd() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "offer-add";
    }

    @PostMapping
    public String postData(
            @Valid OfferAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ){
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

//        TODO:

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectBindingModelAndResult(rAttrs, bindingModel, bindingResult, ATTR_NAME);

            return "redirect:/offers/add";
        }

        Long newOfferId = 0L;

        this.offerService.add(bindingModel);

//        return "redirect:/offers/" + newOfferId;
        return "redirect:/offers/all";
    }
}
