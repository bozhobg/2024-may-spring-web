package bg.softuni.linkedout.service.impl;

import bg.softuni.linkedout.model.dto.EmployeeBasicDTO;
import bg.softuni.linkedout.repository.EmployeeRepository;
import bg.softuni.linkedout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long addEmployee(EmployeeBasicDTO employeeData) {
        return 0L;
    }
}
