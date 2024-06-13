package bg.softuni.linkedout.web;

import bg.softuni.linkedout.model.dto.binding.CompanyBasicDTO;
import bg.softuni.linkedout.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("all")
    public String getAll(Model model) {

        model.addAttribute("companies", companyService.getAllCompaniesView());

        return "company-all";
    }

    @GetMapping("add")
    public String getAdd(Model model) {
        if (!model.containsAttribute("companyData")) {
            model.addAttribute("companyData", new CompanyBasicDTO());
        }

        return "company-add";
    }

    @PostMapping("add")
    public String postAdd(
            @Valid @ModelAttribute(name = "companyData") CompanyBasicDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (bindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("companyData", bindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.companyData",
                    bindingResult
            );

            return "redirect:/companies/add";
        }

        Long companyId;

        try {
            companyId = this.companyService.addCompany(bindingModel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            TODO: handle existing company error. Global error message?
            rAttrs.addFlashAttribute("companyName", bindingModel);

            return "redirect:/companies/add";
        }

        return "redirect:/companies/details/" + companyId;
    }

    @GetMapping("details/{id}")
    public String getDetails(
            @PathVariable("id") Long id,
            Model model
    ) {
//        TODO: how to handle exception from backend, non validation
//        TODO: how to format in template to 2nd decimal sign
        model.addAttribute("companyData", companyService.getCompanyById(id));

        return "company-details";
    }

//    TODO: company delete
    @DeleteMapping("details/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return "redirect:/companies/all";
    }
}
