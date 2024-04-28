package com.example.FilteringStudents.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFoundException(EntityNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());

        return "/views/errorMessage";
    }

    @ExceptionHandler(EntityExistsException.class)
    public String entityExistsException(EntityExistsException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());

        return "/views/errorMessage";
    }
}
