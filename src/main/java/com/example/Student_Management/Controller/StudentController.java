package com.example.Student_Management.Controller;

import com.example.Student_Management.Model.Student;
import com.example.Student_Management.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SimpleTimeZone;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/addStudent")
    public String showForm(Model model){
        model.addAttribute("student",new Student());
        return "StudentForm";
    }

    @PostMapping("/save")
    public String saveStudents(@ModelAttribute("student") Student student){
        service.addStudent(student);
        return "redirect:/";
    }
    @GetMapping("/")
    public String listStudents(Model model) {
        List<Student> students = service.getAllStudents();
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id){
        service.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/editById/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model){
        Student student=service.getStudentById(id);
        System.out.println("Editing student id: "+student.getStudentId());
        model.addAttribute("student",student);
        return "EditForm";
    }
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student){
        service.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping("/getByName")
    public String getSearch(){
        return "SearchByName";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam("query") String query, Model model) {
        List<Student> students = service.searchByName(query);
        model.addAttribute("students", students);
        return "studentList"; // same view as list page
    }   

    /*public String getByName(Model model){
        List<Student> student=service.getByName();
        model.addAttribute("students",student);
        return "StudentList";
    }*/
}
