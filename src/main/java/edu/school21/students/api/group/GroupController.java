package edu.school21.students.api.group;

import edu.school21.students.service.GroupService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    private final GroupMapper groupMapper;

    @PostMapping
    public GroupResponseDTO addGroup(@RequestParam @NotBlank(
            message = "Group name should not be blank")
                                     String groupName) {
        return groupMapper.entityToDTO(groupService.addGroup(groupName));
    }

    @GetMapping
    public List<GroupResponseDTO> getAllGroups() {
        return groupMapper.entityToDTOList(groupService.findAllGroups());
    }

    @GetMapping("{id}")
    public GroupResponseDTO getGroupById(@PathVariable("id")Long id) {
        return groupMapper.entityToDTO(groupService.findGroupById(id));
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable("id")Long id) {
        groupService.deleteGroupById(id);
    }
}
