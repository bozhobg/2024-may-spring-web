package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RouteService {
    @Transactional
    List<RouteShortInfoDTO> getRoutes();
}
