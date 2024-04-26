package com.example.FilteringStudents.dto.student;

import com.example.FilteringStudents.enums.FundingType;
import com.example.FilteringStudents.enums.Gender;
import com.example.FilteringStudents.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPatch {
    private Integer id;
    private String secondName;
    private String name;
    private String surname;
    private FundingType fundingType;
    private Gender gender;
    private Status status;
    private String groupName;
}
