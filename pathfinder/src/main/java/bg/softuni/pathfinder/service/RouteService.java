package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<RouteShortInfoDTO> getRoutes() {

        List<RouteShortInfoDTO> allRoutes = this.routeRepository.findAll()
                .stream()
                .map(this::mapToBasicInfoDto)
                .toList();

        if (allRoutes.isEmpty()) {
//            TODO: throw exception
        }

        return allRoutes;
    }

    private RouteShortInfoDTO mapToBasicInfoDto(Route route) {
//        TODO: set view img
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> optPicture = route.getPictures().stream().findFirst();
        String imageUrl = optPicture.map(Picture::getUrl).orElse(null);
        dto.setImageUrl(imageUrl);

        return dto;
    }
}
