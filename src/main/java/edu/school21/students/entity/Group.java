package edu.school21.students.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "groups")
@Table
public class Group {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groups_seq")
    @SequenceGenerator(
            name = "groups_seq",
            allocationSize = 20)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public Group(String name) {
        this.name = name;
    }
}
