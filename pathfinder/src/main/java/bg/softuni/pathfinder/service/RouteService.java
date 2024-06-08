package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.service.dto.RouteBasicInfoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    final private RouteRepository routeRepository;
    final private ModelMapper modelMapper;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public List<RouteBasicInfoDto> getRoutes() {

        List<RouteBasicInfoDto> allRoutes = this.routeRepository.findAll()
                .stream()
                .map(this::mapToBasicInfoDto)
                .toList();

        if (allRoutes.isEmpty()) {
//            TODO: throw exception
        }

        return allRoutes;
    }

    private RouteBasicInfoDto mapToBasicInfoDto(Route route) {
//        TODO: set view img
        RouteBasicInfoDto dto = modelMapper.map(route, RouteBasicInfoDto.class);

        Optional<Picture> optPicture = route.getPictures().stream().findFirst();
        String imageUrl = optPicture.map(Picture::getUrl).orElse(null);
        dto.setImageUrl(imageUrl);

        return dto;
    }
}
