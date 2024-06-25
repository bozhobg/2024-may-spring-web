package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findAllByOwnerNot(User user);

//    FIXED: custom query for top 2 rated paintings, excluding not voted paintings
    @Query("from Painting p where size(p.ratedByUsers) > 0 order by size(p.ratedByUsers) desc limit 2")
    List<Painting> getTopTwoMostVoted();
}
