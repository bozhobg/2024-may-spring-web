package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.OfferBasicDTO;
import bg.softuni.mobilele.model.dto.OfferPersistDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    Long add(OfferPersistDTO data);

    @Transactional
    Long update(OfferPersistDTO data);

    boolean deleteById(Long offerId);

    @Transactional
    OfferPersistDTO updateData(Long offerId);

    List<OfferBasicDTO> getOffersData();

    @Transactional
    OfferDetailsDTO getDetailsById(Long id);

    @Transactional
    Long getSellerId(Long offerId);
}
