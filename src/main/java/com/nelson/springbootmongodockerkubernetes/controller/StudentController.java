package com.nelson.springbootmongodockerkubernetes.controller;

import com.nelson.springbootmongodockerkubernetes.model.Student;
import com.nelson.springbootmongodockerkubernetes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    private String indexModel(Model model){
        getAllStudents(model);
        return "index";
    }

    @PostMapping("/student")
    public String saveNotes(@RequestParam("image") MultipartFile file,
                            @RequestParam String description,
                            @RequestParam(required = false) String publish,
                            @RequestParam(required = false) String upload,
                            Model model) throws IOException {

        if (publish != null && publish.equals("Publish")) {
            saveStudent(description, model);
            getAllStudents(model);
            return "redirect:/";
        }
        // After save fetch all notes again
        return "index";
    }

    private void saveStudent(String description, Model model){
        if (description != null && !description.trim().isEmpty()) {
            studentRepository.save(new Student(null, description.trim()));
            //After publish you need to clean up the textarea
            model.addAttribute("description", "");
        }
    }

    private void getAllStudents(Model model){
        List<Student> students=studentRepository.findAll();
        Collections.reverse(students);
        model.addAttribute("students", students);
    }


}
