package com.example.Student_Management.Service;

import com.example.Student_Management.Model.Student;
import com.example.Student_Management.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
        @Autowired
    private StudentRepository repo;

    public void addStudent(Student student) {
        repo.save(student);
        System.out.println("Student added");
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
        System.out.println("deleted the entity with id= "+id);
    }

    public Student getStudentById(Long id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Student Not found with id: "+id));
    }

    public void updateStudent(Student student) {
        Student existing = repo.findById(student.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student Not Found with ID: " + student.getStudentId()));

        existing.setName(student.getName());
        existing.setAge(student.getAge());
        existing.setStudentClass(student.getStudentClass());
        existing.setEmail(student.getEmail());
        existing.setAddress(student.getAddress());

        repo.save(existing);
    }

    public List<Student> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    /*public List<Student> getByName() {
        return repo.findByNameContainingIgnoreCase();
    }*/
}
