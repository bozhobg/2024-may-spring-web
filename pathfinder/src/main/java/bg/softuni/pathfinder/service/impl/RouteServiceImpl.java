package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    final private RouteRepository routeRepository;
    final private ModelMapper modelMapper;

    @Autowired
    public RouteServiceImpl(
            RouteRepository routeRepository,
            ModelMapper modelMapper
    ) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteShortInfoDTO> getRoutes() {

        List<RouteShortInfoDTO> allRoutes = this.routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfoDto)
                .toList();

        if (allRoutes.isEmpty()) {
//            TODO: throw exception
        }

        return allRoutes;
    }

    @Override
    public RouteShortInfoDTO getMostCommentedRoute() {
        Route route = this.routeRepository.getMostCommentedRoute();
        RouteShortInfoDTO dto = mapToShortInfoDto(route);

        return dto;
    }

    private RouteShortInfoDTO mapToShortInfoDto(Route route) {
//        TODO: set view img
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> optPicture = route.getPictures().stream().findFirst();
        String imageUrl = optPicture.map(Picture::getUrl).orElse(null);
        dto.setImageUrl(imageUrl);

        return dto;
    }
}
