package bg.softuni.linkedout.web;

import bg.softuni.linkedout.model.dto.binding.EmployeeBasicDTO;
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
    public String getAll(Model model) {
//        TODO: model and view
        model.addAttribute("employees", employeeService.getAllEmployeeView());

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

            return "redirect:/employees/add";
        }

//        TODO: impl add logic and err handling

        long newEmployeeId;

        try {
            newEmployeeId = employeeService.addEmployee(bindingModel);
        } catch (IllegalArgumentException e) {
//            TODO: handle invalid education level, non-existing company
            rAttrs.addFlashAttribute("employeeData", bindingModel);

            return "redirect:/employees/add";
        }

        return "redirect:/employees/details/" + newEmployeeId;
    }

    @GetMapping("details/{id}")
    public String getDetails(@PathVariable("id") Long employeeId, Model model) {
//        TODO: fill TL template, command objects, stick to formatting in html template

        model.addAttribute("employeeData", employeeService.getEmployeeById(employeeId));

        return "employee-details";
    }

//    TODO: employee delete
    @DeleteMapping("delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        return "redirect:/employees/all";
    }
}
