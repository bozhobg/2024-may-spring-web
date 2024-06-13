package bg.softuni.linkedout.model.dto.view;

import java.time.LocalDate;

public record EmployeeShortViewDTO(Long id, String firstName, String lastName, String jobTitle, LocalDate birthDate) {
}
