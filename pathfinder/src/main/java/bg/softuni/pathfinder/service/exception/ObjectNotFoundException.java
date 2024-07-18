package bg.softuni.pathfinder.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found!")
public class ObjectNotFoundException extends RuntimeException {

    private final Long id;

    public ObjectNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
