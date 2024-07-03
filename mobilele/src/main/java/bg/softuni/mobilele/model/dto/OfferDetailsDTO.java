package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Engine;
import bg.softuni.mobilele.model.enums.Transmission;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferDetailsDTO {

    private Long id;

    private Integer year;

    private Integer mileage;

    private BigDecimal price;

    private Engine engine;

    private Transmission transmission;

    private Instant created;

    private Instant modified;

    private Long sellerId;

    private String sellerFirstName;

    private String sellerLastName;

    private String modelName;

    private String modelBrandName;

    private String imageUrl;

    public OfferDetailsDTO() {}

    public Long getId() {
        return id;
    }

    public OfferDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferDetailsDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferDetailsDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferDetailsDTO setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferDetailsDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferDetailsDTO setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferDetailsDTO setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public OfferDetailsDTO setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailsDTO setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailsDTO setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public OfferDetailsDTO setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelBrandName() {
        return modelBrandName;
    }

    public OfferDetailsDTO setModelBrandName(String modelBrandName) {
        this.modelBrandName = modelBrandName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
