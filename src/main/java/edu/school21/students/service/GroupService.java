package edu.school21.students.service;

import edu.school21.students.entity.Group;
import edu.school21.students.exception.GroupNotFoundException;
import edu.school21.students.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group findGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(String.format(
                        "Group with id %d does not exist", id)));
    }

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }
}
