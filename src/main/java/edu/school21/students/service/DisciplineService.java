package edu.school21.students.service;

import edu.school21.students.entity.Discipline;
import edu.school21.students.exception.DisciplineAlreadyExistsException;
import edu.school21.students.exception.DisciplineNotFoundException;
import edu.school21.students.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public Discipline addDiscipline(String disciplineName) {
        Optional<Discipline> optionalDiscipline = disciplineRepository.findByName(disciplineName);
        if (optionalDiscipline.isPresent()) {
            throw new DisciplineAlreadyExistsException(String.format(
                    "Discipline already exists with id %d", optionalDiscipline.get().getId()));
        }
        return disciplineRepository.save(Discipline.builder()
                .name(disciplineName).build());
    }

    public List<Discipline> findAllDisciplines() {
        return disciplineRepository.findAll();
    }


    public Discipline findDisciplineById(Long id) {
        return disciplineRepository.findById(id)
                .orElseThrow(() -> new DisciplineNotFoundException(String.format(
                        "Discipline with id %d does not exist", id)));
    }
}
