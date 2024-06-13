package bg.softuni.linkedout.service.impl;

import bg.softuni.linkedout.model.dto.CompanyBasicDTO;
import bg.softuni.linkedout.model.dto.CompanyShortDTO;
import bg.softuni.linkedout.model.entity.Company;
import bg.softuni.linkedout.repository.CompanyRepository;
import bg.softuni.linkedout.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            ModelMapper modelMapper
    ) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Long addCompany(CompanyBasicDTO companyBasicDTO) throws SQLException {
        boolean doesExist = companyRepository.existsCompanyByName(companyBasicDTO.getName());

        if (doesExist) {
            throw new SQLException("Company name already exists in the database!");
        }

        Company newCompany = modelMapper.map(companyBasicDTO, Company.class);

        return companyRepository.save(newCompany).getId();
    }

    @Override
    public CompanyBasicDTO getCompanyById(Long id) throws NoSuchElementException {

        return modelMapper.map(companyRepository.findById(id).get(), CompanyBasicDTO.class);
    }

    @Override
    public List<CompanyShortDTO> findAllShort() {

        List<CompanyShortDTO> list = companyRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, CompanyShortDTO.class))
                .toList();

        return list;
    }
}
