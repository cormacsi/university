package edu.school21.students.service;

import edu.school21.students.entity.Grade;
import edu.school21.students.exception.GradeNotFoundException;
import edu.school21.students.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public Grade findGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(
                        String.format("Grade with id %d does not exist", id)));
    }

    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }
}
