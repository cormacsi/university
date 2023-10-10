package edu.school21.students.service;

import edu.school21.students.api.grade.GradeRequestDTO;
import edu.school21.students.entity.Discipline;
import edu.school21.students.entity.Grade;
import edu.school21.students.entity.Mark;
import edu.school21.students.entity.Student;
import edu.school21.students.exception.*;
import edu.school21.students.repository.DisciplineRepository;
import edu.school21.students.repository.GradeRepository;
import edu.school21.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    private final StudentRepository studentRepository;

    private final DisciplineRepository disciplineRepository;

    public Grade addGrade(GradeRequestDTO gradeDTO) {
        Student student = studentRepository
                .findStudentByFirstNameAndLastName(
                        gradeDTO.getStudentFirstName(),
                        gradeDTO.getStudentLastName())
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student with name %s and surname %s does not exist",
                                gradeDTO.getStudentFirstName(),
                                gradeDTO.getStudentLastName())));

        Discipline discipline = disciplineRepository
                .findByName(gradeDTO.getDiscipline())
                .orElseThrow(() -> new DisciplineNotFoundException(String.format(
                        "Discipline with name %s does not exist", gradeDTO.getDiscipline())));

        return gradeRepository.save(Grade.builder()
                .mark(Mark.getNameByValue(gradeDTO.getMark()))
                .description(gradeDTO.getDescription())
                .student(student)
                .discipline(discipline).build());
    }

    public Grade findGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(
                        String.format("Grade with id %d does not exist", id)));
    }

    public List<Grade> findAllStudentGrades(Long studentId, LocalDate from, LocalDate to) {
        if (from != null && to != null && from.isAfter(to)) {
            throw new DateInvalidException("The first date should be before the second date");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student with id %d does not exist", studentId)));

        if (from == null && to == null) {
            return student.getGrades().stream()
                    .sorted(Comparator.comparing(Grade::getDate)).collect(Collectors.toList());
        } else if (from == null) {
            return gradeRepository.findGradeByStudentIdAndDateBeforeOrderByDate(studentId, to);
        } else if (to == null) {
            return gradeRepository.findGradeByStudentIdAndDateAfterOrderByDate(studentId, from);
        } else {
            return gradeRepository.findGradeByStudentIdAndDateBetweenOrderByDate(studentId, from, to.plusDays(1));
        }
    }

    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }


    public void deleteGradeById(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new GroupNotFoundException(
                    String.format("Grade with id %d does not exist", id));
        }
        gradeRepository.deleteById(id);
    }
}
