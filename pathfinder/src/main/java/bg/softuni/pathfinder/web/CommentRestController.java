package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.model.dto.CommentDTO;
import bg.softuni.pathfinder.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

//    TODO: how to implement with security? JSON Web Token (JWT)
//    TODO: throw and handle exceptions

    private final CommentService commentService;

    @Autowired
    public CommentRestController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

    @GetMapping("/route/{id}")
    public List<CommentDTO> getComments(
            @PathVariable("id") Long routeId
    ) {

        return this.commentService.getAllForRouteId(routeId);
    }

//   TODO: post over rest, abandon mvc logic

    @PatchMapping("/approve/{id}")
    public ResponseEntity<CommentDTO> approve(
            @PathVariable("id") Long commentId
    ) {
//        TODO: auth

        CommentDTO approved = this.commentService.approve(commentId);

        if (approved == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(approved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") Long commentId
    ) {
//        TODO: auth

        Long delete = this.commentService.delete(commentId);

        if (delete == null) return ResponseEntity.badRequest().body(ErrorMessages.BAD_REQUEST);

        return ResponseEntity.ok(String.format(ErrorMessages.SUCCESSFUL_DELETE_COMMENT, commentId));
    }
}
