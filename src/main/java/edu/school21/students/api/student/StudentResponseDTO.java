package edu.school21.students.api.student;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String groupName;

    private List<String> disciplines;

}
