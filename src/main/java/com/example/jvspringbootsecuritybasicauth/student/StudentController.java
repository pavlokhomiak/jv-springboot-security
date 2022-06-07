package com.example.jvspringbootsecuritybasicauth.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private static final List<Student> STUDENTS = List.of(
      new Student().setId(1L).setName("Bob"),
      new Student().setId(2L).setName("Alise"),
      new Student().setId(3L).setName("Pavlo")
    );

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return STUDENTS.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No student with id " + id));
    }
}
