package edu.school21.students.api.student;

import edu.school21.students.entity.Discipline;
import edu.school21.students.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    protected List<String> mapDisciplinesToList(List<Discipline> disciplines) {
        if (disciplines == null) {
            return new ArrayList<>();
        } else {
            return disciplines.stream()
                    .map(Discipline::getName)
                    .collect(Collectors.toList());
        }
    }

    @Mapping(target = "groupName", source = "student.group.name")
    public abstract StudentResponseDTO entityToDTO(Student student);

    public abstract List<StudentResponseDTO> entityToDTOList(List<Student> students);

}
