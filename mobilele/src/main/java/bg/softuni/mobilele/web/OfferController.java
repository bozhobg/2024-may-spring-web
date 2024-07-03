package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.OfferBasicDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final CurrentUser currentUser;

    @Autowired
    public OfferController(
            OfferService offerService,
            CurrentUser currentUser
    ) {
        this.offerService = offerService;
        this.currentUser = currentUser;
    }

    @GetMapping("/all")
    public String getAll(
            Model model
    ) {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";
//        TODO:

        List<OfferBasicDTO> offersData = this.offerService.getOffersData();

        model.addAttribute("offersData", offersData);

        return "offers";
    }

    @GetMapping("/{id}")
    public String getOfferDetails(
            @PathVariable("id") Long offerId,
            Model model
    ) {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        OfferDetailsDTO detailsById = this.offerService.getDetailsById(offerId);

        if (detailsById == null) return "redirect:/offers/all";

        model.addAttribute("details", detailsById);

//         TODO: currency exchange impl

        return "details";
    }

//    TODO: delete request
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long offerId) {

        if (!currentUser.isLoggedIn()) return "redirect:/users/login";

        boolean success = this.offerService.deleteById(offerId);

        if (!success) return "redirect:/offers/" + offerId;

        return "redirect:/offers/all";
    }

}
