package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import bg.softuni.mobilele.model.dto.BrandWithModelsInfoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BrandService {

    @Transactional
    List<BrandWithModelsBasicDTO> getBrandWithModelsBasicDTOs();

    @Transactional
    List<BrandWithModelsInfoDTO> getBrandsWithModelsInfoDTOs();
}
