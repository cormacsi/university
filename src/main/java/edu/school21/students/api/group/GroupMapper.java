package edu.school21.students.api.group;

import edu.school21.students.entity.Group;
import edu.school21.students.entity.Student;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    protected List<String> mapStudentsToList(List<Student> students) {
        if (students == null) {
            return new ArrayList<>();
        } else {
            return students.stream()
                    .map(student -> String.join(" ", student.getFirstName(), student.getLastName()))
                    .collect(Collectors.toList());
        }
    }

    public abstract GroupResponseDTO entityToDTO(Group group);

    public abstract List<GroupResponseDTO> entityToDTOList(List<Group> groups);
}
