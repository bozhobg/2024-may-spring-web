package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.PaintingAddDTO;
import com.paintingscollectors.model.dto.PaintingInfoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaintingService {

    @Transactional
    boolean addPainting(PaintingAddDTO dto);

    @Transactional
    List<PaintingInfoDTO> getUserPaintings(Long userId);

    @Transactional
    List<PaintingInfoDTO> getUserFavs(Long userId);

    @Transactional
    List<PaintingInfoDTO> getOhterPaintings(Long userId);

    @Transactional
    List<PaintingInfoDTO> getTopVoted();

    void removePainting(Long paintingId);

    @Transactional
    void addToFaves(Long paintingId);

    @Transactional
    void removeFromFav(Long paintingId);

    @Transactional
    void votePainting(Long paintingId);
}
