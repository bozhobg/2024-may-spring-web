package bg.softuni.pathfinder.model.dto;

import java.time.Instant;

public class CommentDTO {
//    TODO: use with REST
//    TODO: dtos for approve and delete -> how to handle roles with approve and delete? Managed backend?
    private Long id;
    private boolean approved;
    private Instant created;
    private String textContent;
    private String authorFullName;
    private String authorUsername;
    private Long routeId;

    public CommentDTO() {}

    public Long getId() {
        return id;
    }

    public CommentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public CommentDTO setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public CommentDTO setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentDTO setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public CommentDTO setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public CommentDTO setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public Long getRouteId() {
        return routeId;
    }

    public CommentDTO setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }
}
