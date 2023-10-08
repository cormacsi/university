package edu.school21.students.api.grade;

import edu.school21.students.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    private final GradeMapper gradeMapper;

    @GetMapping
    public List<GradeDTO> getAllGrades() {
        return gradeMapper.entityToDTOList(gradeService.findAllGrades());
    }


}
