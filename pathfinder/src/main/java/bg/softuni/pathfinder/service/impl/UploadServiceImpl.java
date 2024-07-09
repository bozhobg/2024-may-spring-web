package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.service.UploadService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private final Path gpxBasePath = Path.of(".", "src", "main", "resources" , "storage", "gpx");
    private final Path picBasePath = Path.of(".", "src", "main", "resources" , "storage", "pictures");

    private final CurrentUser currentUser;

    @Autowired
    public UploadServiceImpl(
            CurrentUser currentUser
    ) {
        this.currentUser = currentUser;
    }

    @Override
    public String uploadGpx(MultipartFile gpxFile, Long userId) throws IOException {

        Path subPath = Path.of(userId.toString(), UUID.randomUUID() + ".gpx");
        Path fullRelPath = gpxBasePath.resolve(subPath);

        Files.createDirectories(fullRelPath);

        try {
            Files.copy(gpxFile.getInputStream(), fullRelPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception exc) {
            return null;
        }

        return fullRelPath.toString();
    }

    @Override
    public String uploadPicture(PictureAddDTO dto) throws IOException {

        Long routeId = dto.getRouteId();
        Long userId = this.currentUser.getId();
        String filename = dto.getPicture().getOriginalFilename();

        if (filename == null) return null;

        String fileExt = filename.substring(filename.lastIndexOf('.'));


        Path subPath = Path.of(routeId.toString(), "user_" + userId.toString() + "_id_" + UUID.randomUUID() + fileExt);
        Path fullPath = picBasePath.resolve(subPath);


        try {
            Files.createDirectories(fullPath);
            Files.copy(dto.getPicture().getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception exc) {
            return null;
        }

        return fullPath.toString();
    }
}
