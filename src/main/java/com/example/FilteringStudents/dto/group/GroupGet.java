package com.example.FilteringStudents.dto.group;

import com.example.FilteringStudents.enums.Degree;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupGet {
    private Integer id;
    private Integer course;
    private String name;
    private Degree degree;

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
