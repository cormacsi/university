package edu.school21.students.api.discipline;

import edu.school21.students.entity.Discipline;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DisciplineMapper {

    public abstract DisciplineResponseDTO entityToDTO(Discipline discipline);

    public abstract List<DisciplineResponseDTO> entityToDTOList(List<Discipline> disciplines);
}
