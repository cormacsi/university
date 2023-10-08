package edu.school21.students.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table
public class Grade {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "score_seq")
    @SequenceGenerator(
            name = "score_seq",
            allocationSize = 20)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    private String description;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_grade_student"))
    private Student student;

    @ManyToOne
    @JoinColumn(name = "discipline_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_grade_discipline"))
    private Discipline discipline;

    public Grade(Mark mark, String description, LocalDateTime date, Student student, Discipline discipline) {
        this.mark = mark;
        this.description = description;
        this.date = date;
        this.student = student;
        this.discipline = discipline;
    }
}
