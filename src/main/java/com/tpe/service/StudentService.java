package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository repository;

    //1-c
    @Override
    public List<Student> listAllStudents() {
        return repository.findAll();
    }

    @Override
    public void addOrUpdateStudent(Student student) {

    }

    @Override
    public Student findStudentById(Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }
}
