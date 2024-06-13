package bg.softuni.linkedout.web;

import bg.softuni.linkedout.model.dto.EmployeeBasicDTO;
import bg.softuni.linkedout.model.enums.EducationLevel;
import bg.softuni.linkedout.service.CompanyService;
import bg.softuni.linkedout.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping("all")
    public String getAll() {
//        TODO: model and view
        return "employee-all";
    }

    @GetMapping("add")
    public String getAdd(Model model) {

        if (!model.containsAttribute("employeeData")) {
            model.addAttribute("employeeData", new EmployeeBasicDTO());
        }

        model.addAttribute("educationLevels", EducationLevel.values());
        model.addAttribute("companiesShort", companyService.findAllShort());

        return "employee-add";
    }

    @PostMapping("add")
    public String postAdd(
            @Valid @ModelAttribute(name = "employeeData") EmployeeBasicDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (bindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("employeeData", bindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.employeeData",
                    bindingResult
            );
//            TODO: fill in TL template, command objects
            return "redirect:/employees/add";
        }
//        TODO: impl add logic and err handling
        long newEmployeeId = employeeService.addEmployee(bindingModel);

        return "redirect:/employees/details/" + newEmployeeId;
    }

    @GetMapping("details/{id}")
    public String getDetails(@PathVariable("id") Long employeeId) {
//        TODO: fill TL template, command objects
        return "employee-details";
    }
}
