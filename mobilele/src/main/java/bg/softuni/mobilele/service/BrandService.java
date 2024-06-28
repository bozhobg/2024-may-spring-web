package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BrandService {

    @Transactional
    List<BrandWithModelsBasicDTO> getBrandWithModelsDTOs();
}
