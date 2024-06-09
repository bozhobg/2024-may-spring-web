package bg.softuni.linkedout.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    @GetMapping("all")
    public String getAll() {

        return "employee-all";
    }

    @GetMapping("add")
    public String getAdd() {

        return "employee-add";
    }

//    TODO: add post mapping

    @GetMapping("details/{id}")
    public String getDetails(@PathVariable("id") Long employeeId) {

        return "employee-details";
    }
}
