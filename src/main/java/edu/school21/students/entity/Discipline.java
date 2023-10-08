package edu.school21.students.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(uniqueConstraints = {
                @UniqueConstraint(name = "discipline_name_unique", columnNames = "name")
        }
)
public class Discipline {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "discipline_seq")
    @SequenceGenerator(
            name = "discipline_seq",
            allocationSize = 20)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "disciplines")
    private List<Student> students;

    @OneToMany(mappedBy = "discipline")
    @JsonBackReference
    private List<Grade> grades;

    public Discipline(String name) {
        this.name = name;
    }
}
