package bg.softuni.linkedout.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeBasicDTO {
//    TODO: use/test with record

    @NotBlank(message = "Must not be blank")
    @Size(min = 2, message = "Must be at least 2 letters long")
    private String firstName;

    @NotBlank(message = "Must not be blank")
    @Size(min = 2, message = "Must be at least 2 letters long")
    private String lastName;

//    TODO: custom validation >= 18 years old
    @NotNull(message = "Must not be empty")
    @Past(message = "Must be past date")
    private LocalDate birthDate;

    @NotBlank(message = "Must be selected valid form of completed education level")
    private String educationLevel;

    @NotBlank(message = "Must not be empty")
    private String jobTitle;

    @NotNull(message = "Must not be empty")
    @Positive(message = "Must be a positive number")
    private BigDecimal salary;

//    TODO: select a valid company id, addition db validation. Possible to send wrong value.
    @NotNull(message = "Must be selected a valid company")
    private Long companyId;

    public EmployeeBasicDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
