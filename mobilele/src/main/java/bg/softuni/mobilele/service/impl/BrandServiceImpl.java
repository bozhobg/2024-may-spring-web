package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.BrandWithModelsBasicDTO;
import bg.softuni.mobilele.model.entity.Brand;
import bg.softuni.mobilele.repository.BrandRepository;
import bg.softuni.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandWithModelsBasicDTO> getBrandWithModelsDTOs() {

        return this.brandRepository.findAll()
                .stream()
                .filter(b -> !b.getModels().isEmpty())
                .map(b -> modelMapper.map(b, BrandWithModelsBasicDTO.class))
                .toList();
    }
}
