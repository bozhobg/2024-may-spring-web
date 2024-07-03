package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.constants.ErrorMessages;
import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import bg.softuni.mobilele.validation.ModelValid;
import bg.softuni.mobilele.validation.YearValid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferPersistDTO {
//    TODO: more specific validations

    private Long id;

    @NotEmpty(message = ErrorMessages.NOT_BLANK)
    private String description;

    @NotNull(message = ErrorMessages.INVALID_ENGINE)
    private Engine engine;

    //    TODO: url validator
    @Pattern(
            regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
            message = ErrorMessages.INVALID_URL
    )
    @NotEmpty(message = ErrorMessages.NOT_BLANK)
    private String imageUrl;

    //    min 0 max 900000
    @NotNull(message = ErrorMessages.NOT_BLANK)
    @Min(value = 0, message = ErrorMessages.INVALID_MILEAGE)
    @Max(value = 900000, message = ErrorMessages.INVALID_MILEAGE)
    private Integer mileage;

    @NotNull(message = ErrorMessages.NOT_BLANK)
    @PositiveOrZero(message = ErrorMessages.POSITIVE_VALUE)
    private BigDecimal price;

    @NotNull(message = ErrorMessages.INVALID_TRANSMISSION)
    private Transmission transmission;

//    TODO: validate with model year
    @YearValid(after = 1990, before = 2099)
    private Integer year;

    @ModelValid
    private Long modelId;

    public OfferPersistDTO() {
    }

    public Long getId() {
        return id;
    }

    public OfferPersistDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferPersistDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferPersistDTO setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferPersistDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferPersistDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferPersistDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferPersistDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferPersistDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public OfferPersistDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }
}
