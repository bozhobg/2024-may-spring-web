package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.PictureRepository;
import bg.softuni.pathfinder.model.dto.PictureShortDTO;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(
            PictureRepository pictureRepository,
            ModelMapper modelMapper
    ) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PictureShortDTO> getPictureLinks() {
//        TODO: custom query only by image_url column.

        List<PictureShortDTO> list = this.pictureRepository.findAll()
                .stream()
                .map(this::mapToShort)
                .toList();

        return list;
    }

    @Override
    public List<PictureShortDTO> getRoutePictures(Long routeId) {

        List<PictureShortDTO> list = this.pictureRepository.findByRoute_Id(routeId)
                .stream()
                .map(this::mapToShort)
                .toList();

        return list;
    }

    private PictureShortDTO mapToShort(Picture picture) {
        return modelMapper.map(picture, PictureShortDTO.class);
    }
}
