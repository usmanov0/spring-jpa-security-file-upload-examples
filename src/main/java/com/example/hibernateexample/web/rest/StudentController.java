package com.example.hibernateexample.web.rest;

import com.example.hibernateexample.entity.Student;
import com.example.hibernateexample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @GetMapping("/students")
//    public ResponseEntity hello(){
//        return ResponseEntity.ok("Hello World");
//    }
    // 2
//    @RequestMapping(value = "/student/second", method = RequestMethod.GET)
//    public ResponseEntity students(){
//        return ResponseEntity.ok("Hello World from second");
//    }



    @PostMapping("/students/post")
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        Student result = studentService.post(student);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.ok(studentService.post(student));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.get(id));
    }

    @GetMapping("/students/")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @PutMapping("/student/update")
    public ResponseEntity<Student> putStudent(@RequestParam("id") Long id, @RequestBody Student student){
        return ResponseEntity.ok(studentService.update(id,student));
    }

    @DeleteMapping("/student/delete/{id}")
    public void deleteStudent(@PathVariable ("id") Long id){
        studentService.delete(id);
        ResponseEntity.ok("Student deleted successfully");
    }
}
