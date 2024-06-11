package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.CompanyBasicDTO;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface CompanyService {

    Long addCompany(CompanyBasicDTO companyBasicDTO) throws SQLException;

    CompanyBasicDTO getCompanyById(Long id) throws NoSuchElementException;
}
