package com.example.FilteringStudents.mapper;

import com.example.FilteringStudents.dto.group.GroupGet;
import com.example.FilteringStudents.dto.group.GroupPatch;
import com.example.FilteringStudents.dto.group.GroupPost;
import com.example.FilteringStudents.entity.Group;
import com.example.FilteringStudents.enums.Degree;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public GroupGet groupToGroupGet(Group group) {
        GroupGet groupGet = new GroupGet();

        groupGet.setId(group.getId());
        groupGet.setName(group.getName());
        groupGet.setCourse(group.getCourse());
        groupGet.setDegree(getDegreeText(group.getDegree()));

        return groupGet;
    }

    public Group groupPostToGroup(GroupPost groupPost) {
        Group group = new Group();

        group.setName(groupPost.getName().trim().toUpperCase());
        group.setCourse(groupPost.getCourse());
        group.setDegree(groupPost.getDegree());

        return group;
    }

    public Group groupPatchToGroup(Group group, GroupPatch groupPatch) {
        group.setName(groupPatch.getName().trim().toUpperCase());
        group.setCourse(groupPatch.getCourse());
        group.setDegree(groupPatch.getDegree());

        return group;
    }

    public GroupPatch groupGetToGroupPatch(GroupGet groupGet) {
        GroupPatch groupPatch = new GroupPatch();

        groupPatch.setId(groupGet.getId());
        groupPatch.setName(groupGet.getName());
        groupPatch.setCourse(groupGet.getCourse());
        groupPatch.setDegree(degreeTextToDegree(groupGet.getDegree()));

        return groupPatch;
    }

    public String getDegreeText(Degree degree) {
        switch (degree) {
            case GRADUATE_STUDENT:
                return "Аспирант";
            case SPECIALIST:
                return "Специалист";
            case BACHELOR:
                return "Бакалавр";
            case MASTER:
                return "Магистр";
        }

        return "Бакалавр";
    }

    private Degree degreeTextToDegree(String degree) {
        switch (degree) {
            case "Аспирант":
                return Degree.GRADUATE_STUDENT;
            case "Специалист":
                return Degree.SPECIALIST;
            case "Бакалавр":
                return Degree.BACHELOR;
            case "Магистр":
                return Degree.MASTER;
        }

        return Degree.BACHELOR;
    }
}
