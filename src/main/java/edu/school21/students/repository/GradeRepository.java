package edu.school21.students.repository;

import edu.school21.students.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(value = """
    SELECT *
    FROM grade
    WHERE student_id = ?1
    AND date < ?2
    ORDER BY date;
    """, nativeQuery = true)
    List<Grade> findGradeByStudentIdAndDateBeforeOrderByDate(Long studentId, LocalDate to);

    @Query(value = """
    SELECT *
    FROM grade
    WHERE student_id = ?1 AND date >= ?2
    ORDER BY date;
    """, nativeQuery = true)
    List<Grade> findGradeByStudentIdAndDateAfterOrderByDate(Long studentId, LocalDate from);

    @Query(value = """
    SELECT *
    FROM grade
    WHERE student_id = ?1
    AND date BETWEEN ?2 AND ?3
    ORDER BY date;
    """, nativeQuery = true)
    List<Grade> findGradeByStudentIdAndDateBetweenOrderByDate(Long studentId, LocalDate from, LocalDate to);
}
