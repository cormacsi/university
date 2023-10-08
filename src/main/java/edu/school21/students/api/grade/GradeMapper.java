package edu.school21.students.api.grade;

import edu.school21.students.entity.Grade;
import edu.school21.students.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GradeMapper {

    protected String mapStudentToString(Student student) {
        if (student != null) {
            return String.join(" ", student.getFirstName(), student.getLastName());
        }
        return null;
    }

    @Mapping(target = "mark", source = "grade.mark.value")
    @Mapping(target = "discipline", source = "grade.discipline.name")
    public abstract GradeDTO entityToDTO(Grade grade);

    public abstract List<GradeDTO> entityToDTOList(List<Grade> grades);
}
