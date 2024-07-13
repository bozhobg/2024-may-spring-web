package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.CommentContentPostDTO;
import bg.softuni.pathfinder.model.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllForRouteId(Long routeId);

    CommentDTO approve(Long id);

    boolean add(CommentContentPostDTO bindingModel, Long routeId);

    Long delete(Long commentId);
}
