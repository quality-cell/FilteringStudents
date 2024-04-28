package com.example.FilteringStudents.service;

import com.example.FilteringStudents.dto.group.GroupGet;
import com.example.FilteringStudents.dto.group.GroupPatch;
import com.example.FilteringStudents.dto.group.GroupPost;
import com.example.FilteringStudents.entity.Group;
import com.example.FilteringStudents.enums.Degree;
import com.example.FilteringStudents.mapper.GroupMapper;
import com.example.FilteringStudents.repository.GroupRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private GroupMapper groupMapper;

    @Autowired
    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    public List<GroupGet> getAllGroups() {
        List<GroupGet> groupGet = new ArrayList<>();

        groupRepository.findAll().forEach(group -> groupGet.add(groupMapper.groupToGroupGet(group)));

        if (groupGet.isEmpty()) {
            throw new EntityNotFoundException("Список групп пуст");
        }

        return groupGet;
    }

    public GroupGet getGroupById(Integer id) {
        Optional<Group> groupOptional = groupRepository.findById(id);

        if (groupOptional.isEmpty()) {
            throw new EntityNotFoundException("Группа с id: " + id + " не существует");
        }

        return groupMapper.groupToGroupGet(groupOptional.get());
    }

    public void createGroup(GroupPost groupPost) {
        Optional<Group> groupOptional = groupRepository.findByName(groupPost.getName().toUpperCase().trim());

        if (groupOptional.isPresent()) {
            throw new EntityExistsException("Группа " + groupPost.getName() + " уже существует");
        } else if (!courseCheck(groupPost.getDegree(), groupPost.getCourse())) {
            throw new EntityExistsException("Степень не соответствует курсу обучения");
        }

        groupRepository.save(groupMapper.groupPostToGroup(groupPost));
    }

    public void updateGroup(Integer groupId, GroupPatch groupPatch) {
        Optional<Group> groupOptionalId = groupRepository.findById(groupId);
        Optional<Group> groupOptionalName = groupRepository.findByName(groupPatch.getName());

        if (groupOptionalId.isEmpty()) {
            throw new EntityNotFoundException("Группа с id: " + groupId + " не существует");
        } else if (groupOptionalName.isPresent() && groupOptionalName.get().getId() != groupOptionalId.get().getId()) {
            throw new EntityExistsException("Группа " + groupPatch.getName() + " уже существует");
        } else if (!courseCheck(groupPatch.getDegree(), groupPatch.getCourse())) {
            throw new EntityExistsException("Степень не соответствует курсу обучения");
        }

        groupRepository.save(groupMapper.groupPatchToGroup(groupOptionalId.get(), groupPatch));
    }

    public void deleteGroup(Integer id) {
        Optional<Group> groupOptional = groupRepository.findById(id);

        if (groupOptional.isEmpty()) {
            throw new EntityNotFoundException("Группа с id: " + id + " не существует");
        } else if (groupOptional.get().getStudent() != null && !groupOptional.get().getStudent().isEmpty()) {
            throw new EntityNotFoundException("В группе с id: " + id + " еще есть студенты");
        }

        groupRepository.deleteById(id);
    }

    private boolean courseCheck(Degree degree, Integer course) {
        if (degree == Degree.BACHELOR && course >= 1 && course <= 4 ||
                degree == Degree.GRADUATE_STUDENT && course >= 1 && course <= 4 ||
                degree == Degree.MASTER && course >= 1 && course <= 2 ||
                degree == Degree.SPECIALIST && course >= 1 && course <= 5) {
            return true;
        }

        return false;
    }
}
