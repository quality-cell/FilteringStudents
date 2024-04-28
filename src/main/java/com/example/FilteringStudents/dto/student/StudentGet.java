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
    private String fundingType;
    private String fullName;
    private String gender;
    private String status;
    private Integer course;
    private String degree;
}

