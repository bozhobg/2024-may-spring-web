package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.binding.CompanyBasicDTO;
import bg.softuni.linkedout.model.dto.view.CompanyIdNameViewDTO;
import bg.softuni.linkedout.model.dto.view.CompanyShortViewDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface CompanyService {

    Long addCompany(CompanyBasicDTO companyBasicDTO) throws SQLException;

    CompanyBasicDTO getCompanyById(Long id) throws NoSuchElementException;

    List<CompanyIdNameViewDTO> findAllShort();

    List<CompanyShortViewDTO> getAllCompaniesView();
}
