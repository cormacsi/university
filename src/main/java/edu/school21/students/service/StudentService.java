package edu.school21.students.service;

import edu.school21.students.api.student.StudentRequestDTO;
import edu.school21.students.entity.Group;
import edu.school21.students.entity.Student;
import edu.school21.students.exception.GroupNotFoundException;
import edu.school21.students.exception.StudentNotFoundException;
import edu.school21.students.repository.GroupRepository;
import edu.school21.students.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    public Student addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = Student.builder()
                .firstName(studentRequestDTO.getFirstName())
                .lastName(studentRequestDTO.getLastName())
                .dob(studentRequestDTO.getDob())
                .build();
        if (studentRequestDTO.getGroupName() != null) {
            String groupName = studentRequestDTO.getGroupName();
            Group group = groupRepository.findByName(groupName)
                    .orElseThrow(() -> new GroupNotFoundException(
                            String.format("Group with name %s does not exist", groupName)));
            student.setGroup(group);
        }
        return studentRepository.save(student);
    }


    public List<Student> findStudentsByGroupId(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(
                        String.format("Group with id %d does not exist", id)));

        return group.getStudents();
    }

    public List<Student> findAndSortStudents(String firstName, String lastName, Integer age) {
        List<Student> list = studentRepository.findAll();
        if (firstName != null && firstName.length() > 0) {
            list = list.stream()
                    .filter(s -> firstName.equals(s.getFirstName()))
                    .collect(Collectors.toList());
        }
        if (lastName != null && lastName.length() > 0) {
            list = list.stream()
                    .filter(s -> lastName.equals(s.getLastName()))
                    .collect(Collectors.toList());
        }
        if (age != null) {
            list = list.stream()
                    .filter(s -> age.equals(s.getAge()))
                    .collect(Collectors.toList());
        }
        return list;
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(String.format(
                        "Student with id %d does not exist", id)));
    }

    @Transactional
    public Student addStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(String.format(
                        "Student with id %d does not exist", studentId)));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(String.format(
                        "Group with id %d does not exist", groupId)));

        student.setGroup(group);
        return student;
    }

    @Transactional
    public Student updateStudent(Long id, String firstName, String lastName, LocalDate dob) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(String.format(
                        "Student with id %d does not exist", id)));

        if (firstName != null &&
                firstName.trim().length() > 1 &&
                !Objects.equals(student.getFirstName(), firstName)) {
            student.setFirstName(firstName);
        }

        if (lastName != null &&
                lastName.trim().length() > 1 &&
                !Objects.equals(student.getLastName(), lastName)) {
            student.setLastName(lastName);
        }

        if (dob != null &&
                dob.isBefore(LocalDate.now()) &&
                        !Objects.equals(student.getDob(), dob)) {
            student.setDob(dob);
        }
        return student;
    }

    @Transactional
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(String.format("Student with id %d does not exist", id));
        }
        studentRepository.deleteById(id);
    }
}
