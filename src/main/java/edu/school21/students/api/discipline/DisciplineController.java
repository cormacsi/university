package edu.school21.students.api.discipline;

import edu.school21.students.service.DisciplineService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    private final DisciplineMapper disciplineMapper;

    @PostMapping
    public DisciplineResponseDTO addDiscipline(
            @RequestParam @NotBlank(
                    message = "Discipline name should not be blank")
            String disciplineName) {
        return disciplineMapper.entityToDTO(disciplineService.addDiscipline(disciplineName));
    }

    @GetMapping("{id}")
    public DisciplineResponseDTO getDisciplineById(@PathVariable("id") Long id) {
        return disciplineMapper.entityToDTO(disciplineService.findDisciplineById(id));
    }

    @GetMapping
    public List<DisciplineResponseDTO> getAllDisciplines() {
        return disciplineMapper.entityToDTOList(disciplineService.findAllDisciplines());
    }
}
