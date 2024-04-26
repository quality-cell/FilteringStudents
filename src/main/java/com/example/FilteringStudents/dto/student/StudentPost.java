package com.example.FilteringStudents.dto.student;

import com.example.FilteringStudents.enums.FundingType;
import com.example.FilteringStudents.enums.Gender;
import com.example.FilteringStudents.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPost {
    private String groupName;
    private FundingType fundingType;
    private String secondName;
    private String name;
    private String surname;
    private Gender gender;
    private Status status;
}
