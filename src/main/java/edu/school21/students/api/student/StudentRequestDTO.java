package edu.school21.students.api.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "First name should not be blank")
    private String firstName;

    @NotBlank(message = "Second name should not be blank")
    private String lastName;

    @NotNull
    @Past(message = "The date of birth should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String groupName;
}
