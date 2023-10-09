package edu.school21.students.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Discipline {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "discipline_gen")
    @SequenceGenerator(
            name = "discipline_gen",
            sequenceName = "discipline_id_seq",
            allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "disciplines")
    private List<Student> students;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public Discipline(String name) {
        this.name = name;
    }

    public void addStudentToDiscipline(Student newStudent) {
        this.students.add(newStudent);
        newStudent.getDisciplines().add(this);
    }
}
