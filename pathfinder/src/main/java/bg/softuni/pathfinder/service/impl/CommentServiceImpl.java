package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.CommentRepository;
import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.dto.CommentContentPostDTO;
import bg.softuni.pathfinder.model.dto.CommentDTO;
import bg.softuni.pathfinder.model.entity.Comment;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(
            CommentRepository commentRepository,
            RouteRepository routeRepository,
            UserService userService,
            ModelMapper modelMapper
    ) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentDTO> getAllForRouteId(Long routeId) {

        return this.commentRepository.findAllByRoute_Id(routeId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public boolean add(CommentContentPostDTO bindingModel, Long routeId, Long userId) {

        Route route = this.routeRepository.findById(routeId).orElse(null);

        if (userId == null || route == null) return false;

        User user = this.userService.getById(userId);

        Comment newComment = new Comment()
                .setAuthor(user)
                .setRoute(route)
                .setCreated(Instant.now())
                .setTextContent(bindingModel.getTextContent());

        this.commentRepository.save(newComment);

        return true;
    }

    @Override
    public CommentDTO approve(Long id) {
        Comment byId = this.commentRepository.findById(id)
                .orElse(null);

        if (byId == null) {
            return null;
        }

        byId = this.commentRepository.save(byId.setApproved(true));

        return mapToDTO(byId);
    }


    @Override
    public Long delete(Long commentId) {
//        if (!currentUser.isLogged()) return null;
//
//        User user = this.userService.getById(currentUser.getId());
        Comment comment = this.commentRepository.findById(commentId).orElse(null);

        if (comment == null) return null;

        this.commentRepository.delete(comment);

        return commentId;
    }

    private CommentDTO mapToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

}
