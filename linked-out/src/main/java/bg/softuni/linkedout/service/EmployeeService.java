package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.EmployeeBasicDTO;

public interface EmployeeService {
    Long addEmployee(EmployeeBasicDTO employeeData);
}
