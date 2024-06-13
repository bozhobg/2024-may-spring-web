package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.binding.EmployeeBasicDTO;
import bg.softuni.linkedout.model.dto.view.EmployeeDetailViewDTO;
import bg.softuni.linkedout.model.dto.view.EmployeeShortViewDTO;

import java.util.List;

public interface EmployeeService {
    Long addEmployee(EmployeeBasicDTO employeeData);

    EmployeeDetailViewDTO getEmployeeById(Long employeeId);

    List<EmployeeShortViewDTO> getAllEmployeeView();
}
