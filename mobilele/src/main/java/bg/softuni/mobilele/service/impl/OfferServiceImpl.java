package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.OfferAddDTO;
import bg.softuni.mobilele.model.entity.Offer;
import bg.softuni.mobilele.repository.OfferRepository;
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
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(
            OfferRepository offerRepository,
            UserService userService,
            CurrentUser currentUser,
            ModelMapper modelMapper
    ) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(OfferAddDTO data) {
        Offer newOffer = mapToOfferEntity(data);

        this.offerRepository.save(newOffer);

        return false;
    }

    private Offer mapToOfferEntity(OfferAddDTO data) {
        Offer newEntity = this.modelMapper.map(data, Offer.class);

//        TODO: set creation/modification time, set seller, set year

        newEntity.setCreated(Instant.now())
                .setSeller(this.userService.getUserById(currentUser.getId()))
                .setYear(Year.of(data.getYear()));

        return newEntity;
    }
}
