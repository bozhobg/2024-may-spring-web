package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.CommentContentPostDTO;
import bg.softuni.pathfinder.model.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllForRouteId(Long routeId);

    boolean add(CommentContentPostDTO bindingModel, Long routeId, Long id);

    CommentDTO approve(Long id);

    Long delete(Long commentId);
}
