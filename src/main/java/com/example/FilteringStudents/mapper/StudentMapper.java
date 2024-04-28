package com.example.FilteringStudents.mapper;

import com.example.FilteringStudents.dto.student.StudentGet;
import com.example.FilteringStudents.dto.student.StudentPatch;
import com.example.FilteringStudents.dto.student.StudentPost;
import com.example.FilteringStudents.entity.Group;
import com.example.FilteringStudents.entity.Student;
import com.example.FilteringStudents.enums.Degree;
import com.example.FilteringStudents.enums.FundingType;
import com.example.FilteringStudents.enums.Gender;
import com.example.FilteringStudents.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentPatch studentGetToStudentPatch(StudentGet studentGet) {
        StudentPatch studentPatch = new StudentPatch();
        String[] string = fullNameToSecondNameAndNameAndSurname(studentGet.getFullName());

        studentPatch.setId(studentGet.getId());
        studentPatch.setFundingType(fundingTypeTextToFundingType(studentGet.getFundingType()));
        studentPatch.setName(string[1]);
        studentPatch.setGender(genderTextToGender(studentGet.getGender()));
        studentPatch.setStatus(statusTextToStatus(studentGet.getStatus()));
        studentPatch.setSurname(string[2]);
        studentPatch.setGroupName(studentGet.getGroupName());
        studentPatch.setSecondName(string[0]);

        return studentPatch;
    }

    public Student studentPatchToStudent(Group group, Student student, StudentPatch studentPatch) {
        student.setGender(studentPatch.getGender());
        student.setSurname(firstUpperCase(studentPatch.getSurname().trim()));
        student.setSecondName(firstUpperCase(studentPatch.getSecondName().trim()));
        student.setName(firstUpperCase(studentPatch.getName().trim()));
        student.setGroup(group);
        student.setFundingType(studentPatch.getFundingType());
        student.setStatus(studentPatch.getStatus());

        return student;
    }

    public Student studentPostToStudent(Group group, StudentPost studentPost) {
        Student student = new Student();

        student.setGender(studentPost.getGender());
        student.setSurname(firstUpperCase(studentPost.getSurname().trim()));
        student.setSecondName(firstUpperCase(studentPost.getSecondName().trim()));
        student.setName(firstUpperCase(studentPost.getName().trim()));
        student.setGroup(group);
        student.setFundingType(studentPost.getFundingType());
        student.setStatus(studentPost.getStatus());

        return student;
    }

    public StudentGet studentToStudentGet(Student student) {
        StudentGet studentGet = new StudentGet();

        studentGet.setId(student.getId());
        studentGet.setFullName(getFullName(student.getSecondName(), student.getName(), student.getSurname()));
        studentGet.setGroupName(student.getGroup().getName());
        studentGet.setCourse(student.getGroup().getCourse());
        studentGet.setGender(getGenderText(student.getGender()));
        studentGet.setStatus(getStatusText(student.getStatus()));
        studentGet.setFundingType(getFundingTypeText(student.getFundingType()));
        studentGet.setDegree(getDegreeText(student.getGroup().getDegree()));

        return studentGet;
    }

    private String firstUpperCase(String string) {
        if (string == null || string.isEmpty()) return "";

        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    private String getFullName(String secondName, String name, String surname) {
        return secondName + " " + name + " " + surname;
    }

    private String getGenderText(Gender gender) {
        return gender == Gender.MALE ? "Мужской" : "Женский";
    }

    private String getStatusText(Status status) {
        return status == Status.STUDENT ? "Учащийся" : "Отчисленный";
    }

    private String getFundingTypeText(FundingType fundingType) {
        return fundingType == FundingType.BUDGET ? "На основе бюджетного финансирования" : "На договорной основе";
    }

    private String getDegreeText(Degree degree) {
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

    private String[] fullNameToSecondNameAndNameAndSurname(String fullName) {
        return fullName.split(" ");
    }

    private Gender genderTextToGender(String gender) {
        return gender.equalsIgnoreCase("Мужской") ? Gender.MALE : Gender.FEMALE;
    }

    private Status statusTextToStatus(String status) {
        return status.equalsIgnoreCase("Учащийся") ? Status.STUDENT : Status.EXPELLED;
    }

    private FundingType fundingTypeTextToFundingType(String  fundingType) {
        return fundingType.equalsIgnoreCase("На основе бюджетного финансирования") ? FundingType.BUDGET : FundingType.CONTRACTUAL;
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
