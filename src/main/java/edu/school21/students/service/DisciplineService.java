package edu.school21.students.service;

import edu.school21.students.entity.Discipline;
import edu.school21.students.entity.Student;
import edu.school21.students.exception.DisciplineAlreadyExistsException;
import edu.school21.students.exception.DisciplineNotFoundException;
import edu.school21.students.exception.StudentNotFoundException;
import edu.school21.students.repository.DisciplineRepository;
import edu.school21.students.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    private final StudentRepository studentRepository;

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

    @Transactional
    public void addStudentToDiscipline(Long disciplineId, Long studentId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new DisciplineNotFoundException(String.format(
                        "Discipline with id %d does not exist", disciplineId)));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(String.format(
                        "Student with id %d does not exist", studentId)));
        discipline.addStudentToDiscipline(student);
    }

    @Transactional
    public void deleteDisciplineById(Long id) {
        if (!disciplineRepository.existsById(id)) {
            throw new DisciplineNotFoundException(String.format(
                    "Discipline with id %d does not exist", id));
        }
        disciplineRepository.deleteById(id);
    }
}
