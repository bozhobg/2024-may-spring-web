package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.constants.ErrorMessages;
import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;
import bg.softuni.mobilele.validation.ModelValid;
import bg.softuni.mobilele.validation.YearValid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferAddDTO {
//    TODO: more specific validations

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

    //    TODO: manual set on entity lvl?
    private Instant created;

    //    TODO: manual set on entity lvl?
    private Instant modified;

    @ModelValid
    private Long modelId;

    public OfferAddDTO() {
    }

    public String getDescription() {
        return description;
    }

    public OfferAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferAddDTO setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferAddDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferAddDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferAddDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferAddDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferAddDTO setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferAddDTO setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public OfferAddDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }
}
