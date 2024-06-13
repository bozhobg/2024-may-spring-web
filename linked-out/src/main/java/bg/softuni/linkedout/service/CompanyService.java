package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.CompanyBasicDTO;
import bg.softuni.linkedout.model.dto.CompanyShortDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface CompanyService {

    Long addCompany(CompanyBasicDTO companyBasicDTO) throws SQLException;

    CompanyBasicDTO getCompanyById(Long id) throws NoSuchElementException;

    List<CompanyShortDTO> findAllShort();
}
