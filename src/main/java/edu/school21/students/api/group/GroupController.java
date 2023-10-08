package edu.school21.students.api.group;

import edu.school21.students.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    private final GroupMapper groupMapper;

    @GetMapping
    public List<GroupResponseDTO> getAllGroups() {
        return groupMapper.entityToDTOList(groupService.findAllGroups());
    }

    @GetMapping("{id}")
    public GroupResponseDTO getGroupById(@PathVariable("id")Long id) {
        return groupMapper.entityToDTO(groupService.findGroupById(id));
    }
}
