package com.example.FilteringStudents.entity;

import com.example.FilteringStudents.enums.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student_group")
@Setter
@Getter
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
