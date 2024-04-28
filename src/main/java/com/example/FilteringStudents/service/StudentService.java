package com.example.FilteringStudents.service;

import com.example.FilteringStudents.dto.student.StudentGet;
import com.example.FilteringStudents.dto.student.StudentPatch;
import com.example.FilteringStudents.dto.student.StudentPost;
import com.example.FilteringStudents.entity.Group;
import com.example.FilteringStudents.entity.Student;
import com.example.FilteringStudents.mapper.StudentMapper;
import com.example.FilteringStudents.repository.GroupRepository;
import com.example.FilteringStudents.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentGet> getAllStudents() {
        List<StudentGet> students = new ArrayList<>();

        studentRepository.findAll()
                .forEach(student -> students.add(studentMapper.studentToStudentGet(student)));

        if (students.isEmpty()) {
            throw new EntityNotFoundException("Список студентов пуст");
        }

        students.sort(Comparator.comparing(StudentGet::getCourse));

        return students;
    }

    public StudentGet getStudentById(Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Студента с id: " + id + " не существует");
        }

        return studentMapper.studentToStudentGet(studentOptional.get());
    }

    public List<StudentGet> getByGroupName(String groupName) {
        List<StudentGet> students = new ArrayList<>();
        Optional<Group> group = groupRepository.findByName(groupName);

        if (group.isEmpty()) {
            throw new EntityNotFoundException("Группы " + groupName + " не существует");
        }

        studentRepository.findByGroupId(group.get().getId()).forEach(student -> students.add(studentMapper.studentToStudentGet(student)));

        if (students.isEmpty()) {
            throw new EntityNotFoundException("В группе " + groupName + " нет студентов");
        }

        students.sort(Comparator.comparing(StudentGet::getFullName));

        return students;
    }

    public void createStudent(StudentPost studentPost) {
        if (groupRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("Сначала нужно создать группу");
        }

        groupRepository.findByName(studentPost.getGroupName())
                .ifPresent(group -> studentRepository
                        .save(studentMapper.studentPostToStudent(group, studentPost)));
    }

    public void updateStudent(Integer studentId, StudentPatch studentPatch) {
        Optional<Group> groupOptional = groupRepository.findByName(studentPatch.getGroupName());

        if (groupOptional.isEmpty()) {
            throw new EntityNotFoundException("Группы: " + studentPatch.getGroupName() + " не существует");
        }

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Студента с id: " + studentId + " не существует");
        }

        studentRepository.save(studentMapper.studentPatchToStudent(groupOptional.get(), studentOptional.get(), studentPatch));
    }

    public void deleteStudent(Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Студента с id: " + id + " не существует");
        }

        studentRepository.deleteById(id);
    }
}
