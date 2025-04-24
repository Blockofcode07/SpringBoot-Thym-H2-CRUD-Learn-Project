package com.example.thymeleaf.sprithyme.service;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.sprithyme.entity.Student;
import com.example.thymeleaf.sprithyme.repository.AddressRepository;
import com.example.thymeleaf.sprithyme.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

    private AddressRepository addressRepository;
    private StudentRepository studentRepository;

    public Student findAll(String id) {
        return this.studentRepository.findById(id).orElseThrow();
    }

    public Student save(Student student) {
        this.studentRepository.save(student);
        this.addressRepository.save(student.getAddress());
        return student;
    }   

    public Student update(String id, Student student) {
        Student StudentDatabase = this.findAll(id);
        BeanUtils.copyProperties(student, StudentDatabase, "id", "createdAt", "updatedAt", "address");
        BeanUtils.copyProperties(student.getAddress(), StudentDatabase.getAddress(), "id", "createdAt", "updatedAt", "student");
        return this.studentRepository.save(StudentDatabase);
    }

    public void deleteById(String id) {
        this.studentRepository.delete(this.findAll(id));
    }
    
}
