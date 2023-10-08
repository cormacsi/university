package edu.school21.students.api.student;

import edu.school21.students.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentMapper.entityToDTO(studentService.addStudent(studentRequestDTO));
    }

    @PutMapping("/{studentId}/group/{groupId}")
    public StudentResponseDTO updateStudentsGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        return studentMapper.entityToDTO(studentService.addStudentToGroup(studentId, groupId));
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents(@RequestParam(required = false) String firstName,
                                                   @RequestParam(required = false) String secondName,
                                                   @RequestParam(required = false) Integer age) {
        return studentMapper.entityToDTOList(studentService.findAndSortStudents(firstName, secondName, age));
    }

    @GetMapping("{id}")
    public StudentResponseDTO getStudent(@PathVariable("id") Long id) {
        return studentMapper.entityToDTO(studentService.findStudentById(id));
    }

    @GetMapping("group/{id}")
    public List<StudentResponseDTO> getStudentsByGroupId(@PathVariable("id") Long id) {
        return studentMapper.entityToDTOList(studentService.findStudentsByGroupId(id));
    }

    @PutMapping(path = "{id}")
    public StudentResponseDTO updateStudent(@PathVariable("id")Long id,
                                            @RequestParam(required = false) String firstName,
                                            @RequestParam(required = false) String lastName) {
        return studentMapper.entityToDTO(studentService.updateStudent(id, firstName, lastName));
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable("id")Long id) {
        studentService.deleteStudentById(id);
    }
}
