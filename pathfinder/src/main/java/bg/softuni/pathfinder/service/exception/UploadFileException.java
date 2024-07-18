package bg.softuni.pathfinder.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UploadFileException extends IOException {

    public UploadFileException(String msg) {
        super(msg);
    }
}
