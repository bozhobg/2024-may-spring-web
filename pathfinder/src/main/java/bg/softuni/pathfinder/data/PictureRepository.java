package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findByRoute_Id(Long id);
}
