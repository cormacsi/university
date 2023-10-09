package edu.school21.students.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_gen")
    @SequenceGenerator(
            name = "student_gen",
            sequenceName = "student_id_seq",
            allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate dob;

    @Transient
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "group_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_student_groups"))
    private Group group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;

    @ManyToMany
    @JoinTable(name = "student_discipline",
            joinColumns = @JoinColumn(name = "student_id", foreignKey = @ForeignKey(
                    name = "fk_student_discipline_student_id")),
            inverseJoinColumns = @JoinColumn(name = "discipline_id", foreignKey = @ForeignKey(
                    name = "fk_student_discipline_discipline_id")))
    private List<Discipline> disciplines;

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Student(String firstName, String lastName, LocalDate dob, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.group = group;
    }
}
