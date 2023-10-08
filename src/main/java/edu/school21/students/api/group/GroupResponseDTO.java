package edu.school21.students.api.group;

import lombok.Data;

import java.util.List;

@Data
public class GroupResponseDTO {

    private Long id;

    private String name;

    private List<String> students;
}
