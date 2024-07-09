package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.RouteAddDTO;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RouteService {

    @Transactional
    List<RouteShortInfoDTO> getRoutes();

    @Transactional
    RouteShortInfoDTO getMostCommentedRoute();

    @Transactional
    RouteDetailsDTO getRouteDetails(Long id);

    Long add(RouteAddDTO bindingModel, MultipartFile gpxFile) throws IOException;
}
