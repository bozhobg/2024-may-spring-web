package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.OfferPersistDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.entity.Model;
import bg.softuni.mobilele.model.entity.Offer;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.ModelService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.utils.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserService userService;
    private final ModelService modelService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(
            OfferRepository offerRepository,
            UserService userService,
            ModelService modelService,
            CurrentUser currentUser,
            ModelMapper modelMapper
    ) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.modelService = modelService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long add(OfferPersistDTO data) {
        Offer newOffer = mapToCreateOfferEntity(data);

//        TODO: throw for back-end errors (invalid user, model, although validated at dto lvl), and handle

        return this.offerRepository.save(newOffer).getId();
    }

    @Override
    public Long update(OfferPersistDTO data) {
        Offer existing = this.offerRepository.findById(data.getId()).orElse(null);

        Offer updated = this.mapToUpdatedOfferEntity(data, existing);

        if (updated == null) return null;

        this.offerRepository.save(updated);

        return updated.getId();
    }

    @Override
    public OfferPersistDTO updateData(Long offerId) {
//        TODO: how to segregate handling 3 cases?

        if (offerId == null) return null;

        Offer offer = this.offerRepository.findById(offerId).orElse(null);

        if (offer == null || !offer.getSeller().getId().equals(this.currentUser.getId())) return null;

        OfferPersistDTO dto = this.mapToUpdateView(offer);

        return dto;
    }

    @Override
    public OfferDetailsDTO getDetailsById(Long id) {
        Offer entity = this.offerRepository.findById(id).orElse(null);

        if (entity == null) return null;

        OfferDetailsDTO offerDetailsDTO = this.mapToDetailsView(entity);

        return offerDetailsDTO;
    }

    @Override
    public boolean deleteById(Long offerId) {
        if (offerId == null) return false;

        Offer offer = this.offerRepository.findById(offerId).orElse(null);

        if (offer == null || !offer.getSeller().getId().equals(this.currentUser.getId())) return false;

        this.offerRepository.deleteById(offerId);

        return true;
    }

    @Override
    public Long getSellerId(Long offerId) {

        return this.offerRepository.findById(offerId)
                .map(o -> o.getSeller().getId())
                .orElse(null);
    }

    private Offer mapToCreateOfferEntity(OfferPersistDTO data) {
//        TODO: set creation/modification time, set seller, set year using ModelMapper

        Offer newEntity = this.modelMapper.map(data, Offer.class);

        newEntity.setCreated(Instant.now())
                .setModified(Instant.now())
                .setSeller(this.userService.getUserById(currentUser.getId()))
                .setYear(Year.of(data.getYear()));

        return newEntity;
    }

    private Offer mapToUpdatedOfferEntity(OfferPersistDTO data, Offer existing) {
//        REQ data lacks created, modified, sellerId, year - all null. Form doesn't provide this info
//        TODO: use of model mapper - custom map of props. Quite entangled. How to simplify?

        Model model = this.modelService.getModelById(data.getModelId());

        if (model == null) return null;

        Offer updated = existing
                .setModified(Instant.now())
                .setDescription(data.getDescription())
                .setEngine(data.getEngine())
                .setImageUrl(data.getImageUrl())
                .setMileage(data.getMileage())
                .setPrice(data.getPrice())
                .setTransmission(data.getTransmission())
                .setYear(Year.of(data.getYear()))
                .setModel(model);

        return updated;
    }

    private OfferPersistDTO mapToUpdateView(Offer entity) {

        OfferPersistDTO dto = this.modelMapper.map(entity, OfferPersistDTO.class);

        return dto;
    }

    private OfferDetailsDTO mapToDetailsView(Offer entity) {

        return this.modelMapper.map(entity, OfferDetailsDTO.class);
    }
}
