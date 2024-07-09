package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.dto.RouteAddDTO;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.service.*;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final UploadService uploadService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public RouteServiceImpl(
            RouteRepository routeRepository,
            UserService userService1,
            PictureService pictureService,
            UploadService uploadService, CategoryService categoryService,
            ModelMapper modelMapper,
            CurrentUser currentUser) {
        this.routeRepository = routeRepository;
        this.userService = userService1;
        this.pictureService = pictureService;
        this.uploadService = uploadService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public List<RouteShortInfoDTO> getRoutes() {

        List<RouteShortInfoDTO> allRoutes = this.routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfoDto)
                .toList();

        if (allRoutes.isEmpty()) {
//            TODO:
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

    @Override
    public Long add(RouteAddDTO bindingModel, MultipartFile gpxFile) throws IOException {
//        TODO: disaster

        Route newRoute = mapToEntity(bindingModel, gpxFile);

        if (newRoute == null) return null;

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

    private Route mapToEntity(RouteAddDTO dto, MultipartFile gpxFile) throws IOException {
//        TODO: set categories

        Route newRoute = modelMapper.map(dto, Route.class);

        User user = this.userService.getById(this.currentUser.getId());
        newRoute.setAuthor(user);

        Set<Category> categories = this.categoryService.findByCategoryTypes(dto.getCategories());
        if (categories.isEmpty()) return null;
        newRoute.setCategories(categories);

        String relPath;

        try {
            relPath = this.uploadService.uploadGpx(gpxFile, user.getId());
        } catch (Exception exc) {
            return null;
        }

        newRoute.setGpxCoordinates(relPath);

        return newRoute;
    }
}
