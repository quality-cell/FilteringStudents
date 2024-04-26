package com.example.FilteringStudents.mapper;

import com.example.FilteringStudents.dto.group.GroupGet;
import com.example.FilteringStudents.dto.group.GroupPatch;
import com.example.FilteringStudents.dto.group.GroupPost;
import com.example.FilteringStudents.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public GroupGet groupToGroupGet(Group group) {
        GroupGet groupGet = new GroupGet();
        groupGet.setId(group.getId());
        groupGet.setName(group.getName());
        groupGet.setCourse(group.getCourse());
        groupGet.setDegree(group.getDegree());

        return groupGet;
    }

    public Group groupPostToGroup(GroupPost groupPost) {
        Group group = new Group();
        group.setName(groupPost.getName().toUpperCase().trim());
        group.setCourse(groupPost.getCourse());
        group.setDegree(groupPost.getDegree());

        return group;
    }

    public Group groupPatchToGroup(Group group, GroupPatch groupPatch) {
        group.setName(groupPatch.getName().toUpperCase().trim());
        group.setCourse(groupPatch.getCourse());
        group.setDegree(groupPatch.getDegree());

        return group;
    }

    public GroupPatch groupGetToGroupPatch(GroupGet groupGet) {
        GroupPatch groupPatch = new GroupPatch();
        groupPatch.setId(groupGet.getId());
        groupPatch.setName(groupGet.getName());
        groupPatch.setCourse(groupGet.getCourse());
        groupPatch.setDegree(groupGet.getDegree());

        return groupPatch;
    }
}
