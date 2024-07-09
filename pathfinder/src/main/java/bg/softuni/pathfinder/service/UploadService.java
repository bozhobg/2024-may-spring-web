package bg.softuni.pathfinder.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

public interface UploadService {

    URI uploadGpx(MultipartFile gpxFile, Long routeId) throws IOException;
}
