package com.example.thymeleaf.sprithyme.controller;

import java.util.List;

import com.example.thymeleaf.sprithyme.dto.UpdateStudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.thymeleaf.sprithyme.dto.CreateStudentDTO;
import com.example.thymeleaf.sprithyme.dto.StudentResponseDTO;
import com.example.thymeleaf.sprithyme.dto.mapper.StudentMapper;
import com.example.thymeleaf.sprithyme.repository.StudentRepository;
import com.example.thymeleaf.sprithyme.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @GetMapping
    public ModelAndView showStudents() {
        List<StudentResponseDTO> students = StudentMapper.toDTO(this.studentRepository.findAll());
        return new ModelAndView("students").addObject("students", students);
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm() {
        return new ModelAndView("new-student").addObject("student", new CreateStudentDTO());
    }

    @PostMapping("/new")
    public String createStudent(@ModelAttribute("student") @Valid CreateStudentDTO studentDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "new-student";
        }

        this.studentService.save(StudentMapper.toEntity(studentDTO));
        attributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        StudentResponseDTO responseDTO = StudentMapper.toDTO(this.studentService.findById(id));
        return new ModelAndView("edit-student").addObject("student", responseDTO);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute("student") @Valid UpdateStudentDTO studentDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            studentDTO.setId(id);
            return "edit-student";
        }

        this.studentService.update(id, StudentMapper.toEntity(studentDTO));
        attributes.addFlashAttribute("message", "User updated successfully!");
        return "redirect:/students";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        this.studentService.deleteById(id);
        attributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/students";
    }

}
