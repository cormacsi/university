package edu.school21.students.api.grade;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// TODO: remove data from dto make it final
@Data
public class GradeRequestDTO {

    @NotNull(message = "Mark should not be blank")
    @Min(value = 2, message = "The minimum value is 2")
    @Max(value = 5, message = "The maximum value is 5")
    private Short mark;

    private String description;

    @NotBlank(message = "First name should not be blank")
    private String studentFirstName;

    @NotBlank(message = "Last name should not be blank")
    private String studentLastName;

    @NotBlank(message = "Discipline should not be blank")
    private String discipline;
}
