package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class UploadServiceImpl implements UploadService {

    private final Path gpxBasePath = Path.of(".", "src", "main", "resources" , "storage", "gpx");

    @Override
    public URI uploadGpx(MultipartFile gpxFile, Long routeId) throws IOException {
        Path gpxPath = gpxBasePath.resolve("route_" + routeId + ".gpx");

        try {
            Files.copy(gpxFile.getInputStream(), gpxPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception exc) {
            return null;
        }

        String path = gpxPath.toString();
        URI uri = gpxPath.toUri();


        return uri;
    }
}
