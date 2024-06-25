package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.PaintingAddDTO;
import com.paintingscollectors.model.dto.PaintingInfoDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public PaintingServiceImpl(PaintingRepository paintingRepository, UserRepository userRepository, StyleRepository styleRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.paintingRepository = paintingRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addPainting(PaintingAddDTO dto) {
        User user = this.userRepository.findById(currentUser.getId()).orElse(null);
        Style style = this.styleRepository.findByName(dto.getStyle()).orElse(null);

        if (user == null || style == null) return false;

        this.paintingRepository.save(this.mapToPainting(dto)
                .setOwner(user)
                .setStyle(style));

        return true;
    }

    @Override
    public List<PaintingInfoDTO> getUserPaintings(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) return List.of();

        return user.getPaintings().stream()
                .map(this::mapToPaintingInfo)
                .toList();
    }

    @Override
    public List<PaintingInfoDTO> getUserFavs(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) return List.of();

        return user.getFavoritePaintings()
                .stream()
                .map(this::mapToPaintingInfo)
                .toList();
    }

    @Override
    public List<PaintingInfoDTO> getOhterPaintings(Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) return List.of();

        return this.paintingRepository.findAllByOwnerNot(user)
                .stream()
                .map(this::mapToPaintingInfo)
                .toList();
    }

    @Override
    public List<PaintingInfoDTO> getTopVoted() {
//        FIXED: taking from faved not from voted, reverse sort by votes count and displaying top 2

        return this.paintingRepository.findAll()
                .stream()
                .filter(Painting::getHasVotes)
                .map(this::mapToPaintingInfo)
                .sorted(Collections.reverseOrder(
                        Comparator.comparingInt(PaintingInfoDTO::getVotes)))
                .limit(2)
                .toList();
    }

    @Override
    public void removePainting(Long paintingId) {
        this.paintingRepository.deleteById(paintingId);
    }

    @Override
    public void addToFaves(Long paintingId) {
        User user = this.userRepository.findById(currentUser.getId()).orElse(null);
        Painting painting = this.paintingRepository.findById(paintingId).orElse(null);

        if (user == null || painting == null) return;

//        TODO: use internal entity class logic to set/reset isFaved

        painting.getFavedByUsers().add(user);
        painting.setFavorite(true);

        this.paintingRepository.save(painting);
    }

    @Override
    public void removeFromFav(Long paintingId) {
        User user = this.userRepository.findById(currentUser.getId()).orElse(null);
        Painting painting = this.paintingRepository.findById(paintingId).orElse(null);

        if (user == null || painting == null) return;

        Set<User> favedByUsers = painting.getFavedByUsers();
        favedByUsers.remove(user);

        if (favedByUsers.isEmpty()) {
            painting.setFavorite(false);
        }
        this.paintingRepository.save(painting);
    }

    @Override
    public void votePainting(Long paintingId) {
        Painting painting = this.paintingRepository.findById(paintingId).orElse(null);
        User user = this.userRepository.findById(currentUser.getId()).orElse(null);

        if (painting == null || user == null) return;

//        TODO: use of internal entity class logic to set hasVotes

        painting.getRatedByUsers().add(user);
        painting.setHasVotes(true);

        this.paintingRepository.save(painting);
    }

    private PaintingInfoDTO mapToPaintingInfo(Painting entity) {
        return this.modelMapper.map(entity, PaintingInfoDTO.class)
                .setVotes(entity.getRatedByUsers().size());
    }

    private Painting mapToPainting(PaintingAddDTO dto) {

        return new Painting()
                .setAuthor(dto.getAuthor())
                .setName(dto.getName())
                .setImageUrl(dto.getImageUrl());

    }
}
