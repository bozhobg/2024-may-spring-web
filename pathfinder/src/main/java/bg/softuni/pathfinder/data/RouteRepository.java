package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

//    TODO: limit to approved comments via query - via CommentRepo ?, or post-data layer

    @Query("from Route r order by size(r.comments) desc limit 1")
    Route getMostCommentedRoute();
}
