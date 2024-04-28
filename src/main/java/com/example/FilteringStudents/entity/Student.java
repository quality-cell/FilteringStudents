package com.example.FilteringStudents.entity;

import com.example.FilteringStudents.enums.FundingType;
import com.example.FilteringStudents.enums.Gender;
import com.example.FilteringStudents.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "funding_type")
    @Enumerated(EnumType.STRING)
    private FundingType fundingType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "second_name")
    private String secondName;

    private String name;
    private String surname;
}
