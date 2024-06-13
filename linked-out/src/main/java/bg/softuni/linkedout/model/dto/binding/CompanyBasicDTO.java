package bg.softuni.linkedout.model.dto.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CompanyBasicDTO {

    @NotBlank(message = "Name must not be empty")
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 letters")
    private String name;

    @NotBlank(message = "Town must not be empty")
    @Size(min = 2, max = 10, message = "Town must be between 2 and 10 letters")
    private String town;

    @NotBlank(message = "Description must not be empty")
    @Size(min = 10, message = "Description must be more than 9 letters")
    private String description;

    @NotNull(message = "Budget must not be empty")
    @Positive(message = "Budget must be a positive number")
    private BigDecimal budget;

    public CompanyBasicDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
