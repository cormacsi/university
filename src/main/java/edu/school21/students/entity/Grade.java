package edu.school21.students.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Grade {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grade_gen")
    @SequenceGenerator(
            name = "grade_gen",
            sequenceName = "grade_id_seq",
            allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    private String description;

    @CreationTimestamp
    @Column(nullable = false)
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

    public Grade(Mark mark, String description, Student student, Discipline discipline) {
        this.mark = mark;
        this.description = description;
        this.student = student;
        this.discipline = discipline;
    }
}
