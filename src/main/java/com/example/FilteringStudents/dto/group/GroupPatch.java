package com.example.FilteringStudents.dto.group;

import com.example.FilteringStudents.enums.Degree;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPatch {
    private Integer id;
    private Integer course;
    private String name;
    private Degree degree;
}
