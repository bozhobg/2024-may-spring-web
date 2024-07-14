package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.PictureAddDTO;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface UploadService {

    String uploadGpx(MultipartFile gpxFile, Long userId) throws IOException;

    String uploadPicture(PictureAddDTO dto, Long userId) throws IOException;
}
