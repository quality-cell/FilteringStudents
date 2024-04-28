package com.example.FilteringStudents.entity;

import com.example.FilteringStudents.enums.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "student_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "group")
    private List<Student> student;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    private Integer course;
    private String name;
}
