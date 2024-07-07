package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RouteService {

    @Transactional
    List<RouteShortInfoDTO> getRoutes();

    @Transactional
    RouteShortInfoDTO getMostCommentedRoute();

    @Transactional
    RouteDetailsDTO getRouteDetails(Long id);
}
