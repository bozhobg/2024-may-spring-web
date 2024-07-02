package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.OfferAddDTO;
import bg.softuni.mobilele.model.entity.Model;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(
            ModelRepository modelRepository,
            ModelMapper modelMapper
    ) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean existsById(Long id) {
        return this.modelRepository.existsById(id);
    }


}
