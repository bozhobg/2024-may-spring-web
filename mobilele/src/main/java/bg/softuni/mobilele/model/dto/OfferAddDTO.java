package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.entity.Model;
import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Year;

public class OfferAddDTO {

    private String description;

    private Engine engine;

    private String imageUrl;

    private Integer mileage;

    private BigDecimal price;

    private Transmission transmission;

    private Year year;

    private Instant created;

    private Instant modified;

    private Long modelId;
}
