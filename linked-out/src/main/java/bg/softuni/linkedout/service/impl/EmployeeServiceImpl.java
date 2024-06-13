package bg.softuni.linkedout.service.impl;

import bg.softuni.linkedout.model.dto.binding.EmployeeBasicDTO;
import bg.softuni.linkedout.model.dto.view.EmployeeDetailViewDTO;
import bg.softuni.linkedout.model.dto.view.EmployeeShortViewDTO;
import bg.softuni.linkedout.model.entity.Employee;
import bg.softuni.linkedout.model.enums.EducationLevel;
import bg.softuni.linkedout.repository.CompanyRepository;
import bg.softuni.linkedout.repository.EmployeeRepository;
import bg.softuni.linkedout.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            CompanyRepository companyRepository,
            ModelMapper modelMapper
    ) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long addEmployee(EmployeeBasicDTO employeeData) {
//        TODO: model mapper to map info from DB i.e. companyId -> company entity, Education enum value

        // maps just Company.id in a new obj (other fields null), which is enough to make the relation
        Employee entity = modelMapper.map(employeeData, Employee.class);

//        TODO: EXCs on invalid company or enum value (under same EXC)
        entity.setEductionLevel(EducationLevel.valueOf(employeeData.getEducationLevel()));

        if (!companyRepository.existsById(employeeData.getCompanyId())) {
            throw new IllegalArgumentException();
        }

        return employeeRepository
                .save(entity)
                .getId();
    }

    @Override
    public EmployeeDetailViewDTO getEmployeeById(Long employeeId) {
//        TODO: handle non-existent id
        return modelMapper.map(employeeRepository.findById(employeeId).get(), EmployeeDetailViewDTO.class);
    }

    @Override
    public List<EmployeeShortViewDTO> getAllEmployeeView() {

        List<EmployeeShortViewDTO> employeesData = employeeRepository.findAll()
                .stream()
                .map(e -> new EmployeeShortViewDTO(
                        e.getId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle(),
                        e.getBirthdate()))
                .toList();

        return employeesData;
    }
}
