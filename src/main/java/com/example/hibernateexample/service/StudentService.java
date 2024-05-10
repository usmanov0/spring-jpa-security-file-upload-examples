package com.example.hibernateexample.service;

import com.example.hibernateexample.exception.UserNotExistException;
import com.example.hibernateexample.entity.Student;
import com.example.hibernateexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository1) {
        this.studentRepository = studentRepository1;
    }

    public Student post(Student student) {
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student update(Long id,Student updateStudent) {
        Optional<Student> existUser = studentRepository.findById(id);
        if (!existUser.isPresent()) {
                throw new UserNotExistException("user not found with id");
            }else{
                existUser.get().setName(updateStudent.getName());
                existUser.get().setSurname(updateStudent.getSurname());
                existUser.get().setAge(updateStudent.getAge());
            }
            return studentRepository.save(existUser.get());
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
