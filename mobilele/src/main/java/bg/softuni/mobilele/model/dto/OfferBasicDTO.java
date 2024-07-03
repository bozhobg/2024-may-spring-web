package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;

import java.math.BigDecimal;

public class OfferBasicDTO {

    private Long id;

    private Integer year;

    private String modelBrandName;

    private String modelName;

    private Integer mileage;

    private BigDecimal price;

    private Engine engine;

    private Transmission transmission;

    private String imageUrl;

    public OfferBasicDTO() {}

    public Long getId() {
        return id;
    }

    public OfferBasicDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferBasicDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getModelBrandName() {
        return modelBrandName;
    }

    public OfferBasicDTO setModelBrandName(String modelBrandName) {
        this.modelBrandName = modelBrandName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public OfferBasicDTO setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferBasicDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferBasicDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferBasicDTO setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferBasicDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferBasicDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
