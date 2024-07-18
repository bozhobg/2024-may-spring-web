package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import bg.softuni.pathfinder.service.UploadService;
import bg.softuni.pathfinder.service.exception.UploadFileException;
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

    private final Path gpxBasePath = Path.of(".", "src", "main", "resources", "storage", "gpx");
    private final Path picBasePath = Path.of(".", "src", "main", "resources", "storage", "pictures");


    @Override
    public String uploadGpx(MultipartFile gpxFile, Long userId) throws IOException {

        String fileName = gpxFile.getOriginalFilename();
        int extIndex = fileName.lastIndexOf('.');

        if (extIndex == -1) throw new UploadFileException("Invalid gpx file extension!");

        String extension = fileName.substring(extIndex);

        if (!extension.equals(".gpx")) throw new UploadFileException("Invalid gpx file extension!");

        Path subPath = Path.of(userId.toString(), UUID.randomUUID() + ".gpx");
        Path fullRelPath = gpxBasePath.resolve(subPath);

        Files.createDirectories(fullRelPath);
        Files.copy(gpxFile.getInputStream(), fullRelPath, StandardCopyOption.REPLACE_EXISTING);

        return fullRelPath.toString();
    }

    @Override
    public String uploadPicture(PictureAddDTO dto, Long userId) throws IOException {

        Long routeId = dto.getRouteId();
        String filename = dto.getPicture().getOriginalFilename();

        if (filename == null) throw new UploadFileException("Invalid picture filename!");

        int fileExtIndex = filename.lastIndexOf('.');
        if (fileExtIndex == -1) throw new UploadFileException("Invalid picture extension!");
        //TODO: check extension types

        String fileExt = filename.substring(fileExtIndex);

        Path subPath = Path.of(routeId.toString(), "user_" + userId.toString() + "_id_" + UUID.randomUUID() + fileExt);
        Path fullPath = picBasePath.resolve(subPath);

        Files.createDirectories(fullPath);
        Files.copy(dto.getPicture().getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);

        return "/pictures/" + subPath;
    }
}
