package com.example.FilteringStudents.dto.student;

import com.example.FilteringStudents.enums.Degree;
import com.example.FilteringStudents.enums.FundingType;
import com.example.FilteringStudents.enums.Gender;
import com.example.FilteringStudents.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGet {
    private Integer id;
    private String groupName;
    private FundingType fundingType;
    private String secondName;
    private String name;
    private String surname;
    private Gender gender;
    private Status status;
    private Integer course;
    private Degree degree;

    public String getFullName() {
        return secondName + " " + name + " " + surname;
    }

    public String getGenderText() {
        return gender == Gender.MALE ? "Мужской" : "Женский";
    }

    public String getStatusText() {
        return status == Status.STUDENT ? "Учащийся" : "Отчисленный";
    }

    public String getFundingTypeText() {
        return fundingType == FundingType.BUDGET ? "На основе бюджетного финансирования" : "На договорной основе";
    }

    public String getDegreeText() {
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

        return "";
    }
}

