package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.PictureRepository;
import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.model.dto.PictureShortDTO;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.UploadService;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final UploadService uploadService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(
            PictureRepository pictureRepository,
            UploadService uploadService,
            UserService userService,
            ModelMapper modelMapper
    ) {
        this.pictureRepository = pictureRepository;
        this.uploadService = uploadService;
        this.userService = userService;
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

    @Override
    public boolean add(PictureAddDTO dto, Long userId) throws IOException {

        String path = this.uploadService.uploadPicture(dto, userId);
        Picture newPicture = mapToEntity(dto, userId);


        newPicture.setUrl(path);
        this.pictureRepository.save(newPicture);

        return true;
    }

    private PictureShortDTO mapToShort(Picture picture) {
        return modelMapper.map(picture, PictureShortDTO.class);
    }

    private Picture mapToEntity(PictureAddDTO dto, Long userId) {

        Picture entity = modelMapper.map(dto, Picture.class);
        User user = this.userService.getById(userId);
        entity.setAuthor(user);
        entity.setId(null); // mapped by mm

        return entity;
    }
}
