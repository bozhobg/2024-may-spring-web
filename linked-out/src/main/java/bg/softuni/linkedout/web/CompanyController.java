package bg.softuni.linkedout.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("companies")
public class CompanyController {

    @GetMapping("all")
    public String getAll() {

        return "company-all";
    }

    @GetMapping("add")
    public String getAdd() {

        return "company-add";
    }

//    TODO: add post mapping

    @GetMapping("details/{id}")
    public String getDetails(@PathVariable("id") Long id) {
        return "company-details";
    }
}
