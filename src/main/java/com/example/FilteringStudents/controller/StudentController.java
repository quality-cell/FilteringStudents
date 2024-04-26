package com.example.FilteringStudents.controller;

import com.example.FilteringStudents.dto.student.StudentPatch;
import com.example.FilteringStudents.dto.student.StudentPost;
import com.example.FilteringStudents.mapper.StudentMapper;
import com.example.FilteringStudents.service.GroupService;
import com.example.FilteringStudents.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    private GroupService groupService;
    private StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/all")
    public String getAllStudents(@RequestParam(name = "groupName", required = false) String groupName, Model model) {
        try {
            if (groupName == null || groupName.isEmpty()) {
                model.addAttribute("studentGet", studentService.getAllStudents());
            } else {
                model.addAttribute("studentGet", studentService.getByGroupName(groupName));
            }
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        model.addAttribute("groupGet", groupService.getAllGroups());

        return "/views/student/studentList";
    }

    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable int studentId, Model model) {
        try {
            model.addAttribute("studentGet", studentService.getStudentById(studentId));
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        return "/views/student/studentByIndex";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("studentPost") StudentPost studentPost, Model model) {
        try {
            model.addAttribute("groupGet", groupService.getAllGroups());
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        return "/views/student/new";
    }

    @PostMapping
    public String createStudent(@ModelAttribute("studentPost") @Validated StudentPost studentPost, Model model) {
        try {
            studentService.createStudent(studentPost);
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        return "redirect:/";
    }

    @GetMapping("/{studentId}/edit")
    public String editStudent(Model model, @PathVariable("studentId") Integer studentId) {
        try {
            model.addAttribute("studentPatch", studentMapper.studentGetToStudentPatch(studentService.getStudentById(studentId)));
            model.addAttribute("groupGet", groupService.getAllGroups());
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        return "/views/student/edit";
    }

    @PatchMapping("/{studentId}")
    public String updateStudent(@PathVariable("studentId") Integer studentId, @ModelAttribute("studentPatch") @Validated StudentPatch studentPatch, Model model) {
        try {
            studentService.updateStudent(studentId, studentPatch);
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        return "redirect:/";
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Integer studentId, Model model) {
        try {
            studentService.deleteStudent(studentId);
        } catch (EntityNotFoundException exception) {
            model.addAttribute("errorMessage", exception.getMessage());

            return "/views/errorMessage";
        }

        return "redirect:/";
    }
}
