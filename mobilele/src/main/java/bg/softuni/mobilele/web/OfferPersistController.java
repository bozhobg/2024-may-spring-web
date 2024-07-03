package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import bg.softuni.mobilele.model.dto.OfferPersistDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferPersistController {

    private static final String ATTR_ADD_NAME = "offerData";
    private static final String ATTR_PUT_NAME = "updateData";

    private final BrandService brandService;
    private final OfferService offerService;
    private final CurrentUser currentUser;

    @Autowired
    public OfferPersistController(
            BrandService brandService,
            OfferService offerService,
            CurrentUser currentUser
    ) {
        this.brandService = brandService;
        this.offerService = offerService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(ATTR_ADD_NAME)
    public OfferPersistDTO offerData() {
        return new OfferPersistDTO();
    }

    @ModelAttribute("brandsData")
    public List<BrandWithModelsBasicDTO> getBrandsAndModels() {
        return this.brandService.getBrandWithModelsBasicDTOs();
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

    @GetMapping("/add")
    public String getAdd() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "offer-add";
    }

    @PostMapping("/add")
    public String postData(
            @Valid OfferPersistDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectBindingModelAndResult(rAttrs, bindingModel, bindingResult, ATTR_ADD_NAME);

            return "redirect:/offers/add";
        }

        Long newOfferId = this.offerService.add(bindingModel);
        ;

        return "redirect:/offers/" + newOfferId;
    }

    //    TODO: get mapping with filled form
    @GetMapping("/update/{id}")
    public String getUpdateOffer(
            @PathVariable("id") Long offerId,
            Model model
    ) {
        if (!currentUser.isLoggedIn()) return "redirect:/users/login";

        OfferPersistDTO bindingModel = this.offerService.updateData(offerId);

        if (bindingModel == null) return "redirect:/offers/" + offerId;

        if (!model.containsAttribute(ATTR_PUT_NAME)) {
            model.addAttribute(ATTR_PUT_NAME, bindingModel);
        }

        return "offer-update";
    }

    //    TODO: update request -> How? (using add logic?)
    @PutMapping("/update/{id}")
    public String putUpdateOffer(
            @PathVariable("id") Long offerId, // binding model takes offer id from path var??
            @Valid OfferPersistDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
//        TODO:
        if (!currentUser.isLoggedIn()) return "redirect:/users/login";

        if (!currentUser.getId().equals(this.offerService.getSellerId(offerId))) {
            return "redirect:/offers/" + offerId;
        }

//        TODO: REQ data lacks created, modified, sellerId - all null. Form doesn't provide this info

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectBindingModelAndResult(rAttrs, bindingModel, bindingResult, ATTR_PUT_NAME);

            return "redirect:/offers/update/" + offerId;
        }

        this.offerService.update(bindingModel);

        return "redirect:/offers/" + offerId;
    }
}
