package edu.school21.students.service;

import edu.school21.students.entity.Grade;
import edu.school21.students.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }
}
