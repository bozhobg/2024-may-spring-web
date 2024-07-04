package bg.softuni.pathfinder.service;


import bg.softuni.pathfinder.model.dto.PictureShortDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PictureService {

    @Transactional
    List<PictureShortDTO> getPictureLinks();
}
