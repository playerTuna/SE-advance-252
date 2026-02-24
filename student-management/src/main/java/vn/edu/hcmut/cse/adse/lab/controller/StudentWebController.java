package vn.edu.hcmut.cse.adse.lab.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;

        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);

        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable UUID id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    @PostMapping
    public String createStudent(Student student) {
        service.create(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "student-form";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable UUID id, Student formStudent) {
        service.update(id, formStudent);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable UUID id) {
        service.delete(id);
        return "redirect:/students";
    }
}
