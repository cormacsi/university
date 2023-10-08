package edu.school21.students.api.grade;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GradeDTO {

    private Long id;

    private Integer mark;

    private String description;

    private LocalDateTime date;

    private String student;

    private String discipline;
}
