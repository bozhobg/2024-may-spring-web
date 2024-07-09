package bg.softuni.pathfinder.service;


import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.model.dto.PictureShortDTO;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface PictureService {

    @Transactional
    List<PictureShortDTO> getPictureLinks();

    List<PictureShortDTO> getRoutePictures(Long routeId);

    @Transactional
    boolean add(PictureAddDTO bindingModel) throws IOException;
}
