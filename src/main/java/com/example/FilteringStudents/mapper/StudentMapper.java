package com.example.FilteringStudents.mapper;

import com.example.FilteringStudents.dto.student.StudentGet;
import com.example.FilteringStudents.dto.student.StudentPatch;
import com.example.FilteringStudents.dto.student.StudentPost;
import com.example.FilteringStudents.entity.Group;
import com.example.FilteringStudents.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentPatch studentGetToStudentPatch(StudentGet studentGet) {
        StudentPatch studentPatch = new StudentPatch();
        studentPatch.setId(studentGet.getId());
        studentPatch.setFundingType(studentGet.getFundingType());
        studentPatch.setName(studentGet.getName());
        studentPatch.setGender(studentGet.getGender());
        studentPatch.setStatus(studentGet.getStatus());
        studentPatch.setSurname(studentGet.getSurname());
        studentPatch.setGroupName(studentGet.getGroupName());
        studentPatch.setSecondName(studentGet.getSecondName());

        return studentPatch;
    }

    public Student studentPatchToStudent(Group group, Student student, StudentPatch studentPatch) {
        student.setGender(studentPatch.getGender());
        student.setSurname(firstUpperCase(studentPatch.getSurname()).trim());
        student.setSecondName(firstUpperCase(studentPatch.getSecondName()).trim());
        student.setName(firstUpperCase(studentPatch.getSecondName()).trim());
        student.setGroup(group);
        student.setFundingType(studentPatch.getFundingType());
        student.setStatus(studentPatch.getStatus());

        return student;
    }

    public Student studentPostToStudent(Group group, StudentPost studentPost) {
        Student student = new Student();
        student.setGender(studentPost.getGender());
        student.setSurname(firstUpperCase(studentPost.getSurname()).trim());
        student.setSecondName(firstUpperCase(studentPost.getSecondName()).trim());
        student.setName(firstUpperCase(studentPost.getSecondName()).trim());
        student.setGroup(group);
        student.setFundingType(studentPost.getFundingType());
        student.setStatus(studentPost.getStatus());

        return student;
    }

    public StudentGet studentToStudentGet(Student student) {
        StudentGet studentGet = new StudentGet();
        studentGet.setId(student.getId());
        studentGet.setName(student.getName());
        studentGet.setGroupName(student.getGroup().getName());
        studentGet.setCourse(student.getGroup().getCourse());
        studentGet.setSecondName(student.getSecondName());
        studentGet.setSurname(student.getSurname());
        studentGet.setGender(student.getGender());
        studentGet.setStatus(student.getStatus());
        studentGet.setFundingType(student.getFundingType());
        studentGet.setDegree(student.getGroup().getDegree());

        return studentGet;
    }

    private String firstUpperCase(String string) {
        if (string == null || string.isEmpty()) return "";

        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
