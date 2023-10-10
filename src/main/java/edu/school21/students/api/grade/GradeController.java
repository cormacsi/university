package edu.school21.students.api.grade;

import edu.school21.students.service.GradeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    private final GradeMapper gradeMapper;

    @GetMapping
    public List<GradeResponseDTO> getAllGrades() {
        return gradeMapper.entityToDTOList(gradeService.findAllGrades());
    }

    @GetMapping("{id}")
    public GradeResponseDTO getGrade(@PathVariable Long id) {
        return gradeMapper.entityToDTO(gradeService.findGradeById(id));
    }

    @GetMapping("student/{studentId}")
    public List<GradeResponseDTO> getAllStudentGrades(
            @PathVariable Long studentId,
            @RequestParam(required = false) @PastOrPresent(message = "Date should be past or present") LocalDate from,
            @RequestParam(required = false) @PastOrPresent(message = "Date should be past or present") LocalDate to) {
        return gradeMapper.entityToDTOList(gradeService.findAllStudentGrades(studentId, from, to));
    }

    @PostMapping
    public GradeResponseDTO addGrade(@Valid @RequestBody GradeRequestDTO gradeRequestDTO) {
        return gradeMapper.entityToDTO(gradeService.addGrade(gradeRequestDTO));
    }

    @DeleteMapping("{id}")
    public void deleteGrade(@PathVariable Long id) {
        gradeService.deleteGradeById(id);
    }

}
