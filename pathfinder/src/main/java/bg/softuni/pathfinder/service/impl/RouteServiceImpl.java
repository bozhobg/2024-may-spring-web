package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.dto.RouteAddDTO;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.user.AppUserDetails;
import bg.softuni.pathfinder.service.*;
import bg.softuni.pathfinder.service.exception.ObjectNotFoundException;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final UploadService uploadService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public RouteServiceImpl(
            RouteRepository routeRepository,
            UserService userService,
            UploadService uploadService,
            CategoryService categoryService,
            ModelMapper modelMapper
    ) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.uploadService = uploadService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteShortInfoDTO> getRoutes() {

        List<RouteShortInfoDTO> allRoutes = this.routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfoDto)
                .toList();

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
        Route route = this.routeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Route with id: " + id + ", not found!", id));

        return mapToRouteDetails(route);
    }

    @Override
    public Long add(RouteAddDTO bindingModel, MultipartFile gpxFile, Long userId) throws IOException {
//        TODO: disaster

        Route newRoute = mapToEntity(bindingModel, gpxFile, userId);

        Long routeId = this.routeRepository.save(newRoute).getId();

        return routeId;
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

    private Route mapToEntity(
            RouteAddDTO dto,
            MultipartFile gpxFile,
            Long userid
    ) throws IOException {

        Route newRoute = modelMapper.map(dto, Route.class);

        User user = this.userService.getById(userid);
        newRoute.setAuthor(user);

        Set<Category> categories = this.categoryService.findByCategoryTypes(dto.getCategories());

        if (categories.isEmpty()) throw new IllegalArgumentException("Invalid category selection!");

        newRoute.setCategories(categories);

        String relPath = this.uploadService.uploadGpx(gpxFile, user.getId());

        newRoute.setGpxCoordinates(relPath);

        return newRoute;
    }
}
