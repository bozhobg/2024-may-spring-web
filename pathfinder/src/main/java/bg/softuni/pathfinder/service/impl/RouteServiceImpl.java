package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    final private RouteRepository routeRepository;
    final private PictureService pictureService;
    final private ModelMapper modelMapper;

    @Autowired
    public RouteServiceImpl(
            RouteRepository routeRepository,
            PictureService pictureService,
            ModelMapper modelMapper
    ) {
        this.routeRepository = routeRepository;
        this.pictureService = pictureService;
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

    @Override
    public RouteDetailsDTO getRouteDetails(Long id) {
        Route route = this.routeRepository.findById(id).orElse(null);

        if (route == null) return null;

        return mapToRouteDetails(route);
    }

    private RouteShortInfoDTO mapToShortInfoDto(Route route) {
//        TODO: set view img
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> optPicture = route.getPictures().stream().findFirst();
        String imageUrl = optPicture.map(Picture::getUrl).orElse(null);
        dto.setImageUrl(imageUrl);

        return dto;
    }

    private RouteDetailsDTO mapToRouteDetails(Route route) {

        return modelMapper.map(route, RouteDetailsDTO.class);
//                .setPictures(this.pictureService.getRoutePictures(route.getId()));
    }
}
