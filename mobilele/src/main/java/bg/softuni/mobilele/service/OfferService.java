package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.OfferPersistDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OfferService {

    Long add(OfferPersistDTO data);

    @Transactional
    Long update(OfferPersistDTO data);

    @Transactional
    OfferPersistDTO updateData(Long offerId);

    @Transactional
    OfferDetailsDTO getDetailsById(Long id);

    boolean deleteById(Long offerId);

    @Transactional
    Long getSellerId(Long offerId);
}
