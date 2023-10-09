package edu.school21.students.service;

import edu.school21.students.entity.Group;
import edu.school21.students.exception.GroupAlreadyExistsException;
import edu.school21.students.exception.GroupNotFoundException;
import edu.school21.students.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group addGroup(String groupName) {
        Optional<Group> groupOptional = groupRepository.findByName(groupName);
        if (groupOptional.isPresent()) {
            throw new GroupAlreadyExistsException(String.format(
                    "Group already exists with id %d", groupOptional.get().getId()));
        }

        return groupRepository.save(Group.builder().name(groupName).build());
    }

    public Group findGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(String.format(
                        "Group with id %d does not exist", id)));
    }

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public void deleteGroupById(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException(String.format(
                    "Group with id %d does not exist", id));
        }
        groupRepository.deleteById(id);
    }
}
