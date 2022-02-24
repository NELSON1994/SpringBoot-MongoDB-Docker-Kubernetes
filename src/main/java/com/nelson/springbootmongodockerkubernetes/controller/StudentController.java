package com.nelson.springbootmongodockerkubernetes.controller;

import com.nelson.springbootmongodockerkubernetes.configs.StudentConfigs;
import com.nelson.springbootmongodockerkubernetes.model.Student;
import com.nelson.springbootmongodockerkubernetes.repository.StudentRepository;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConfigs properties;

    private Parser parser = Parser.builder().build();
    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    @GetMapping("/all")
    private String indexModel(Model model){
        getAllStudents(model);
        return "index";
    }

    @PostMapping("/student")
    public String saveNotes(@RequestParam("image") MultipartFile file,
                            @RequestParam String description,
                            @RequestParam(required = false) String publish,
                            @RequestParam(required = false) String upload,
                            Model model) throws Exception {

        if (publish != null && publish.equals("Publish")) {
            saveStudent(description, model);
            getAllStudents(model);
            return "redirect:/student/all";
        }
        if (upload != null && upload.equals("Upload")) {
            if (file != null && file.getOriginalFilename() != null
                    && !file.getOriginalFilename().isEmpty()) {
                uploadImage(file, description, model);
            }
            getAllStudents(model);
            return "index";
        }
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

    private void uploadImage(MultipartFile file, String description, Model model) throws Exception {
        File uploadsDir = new File(properties.getUploadDir());
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
        String fileId = UUID.randomUUID().toString() + "."
                + file.getOriginalFilename().split("\\.")[1];
        file.transferTo(new File(properties.getUploadDir() + fileId));
        model.addAttribute("description", description + " ![](/uploads/" + fileId + ")");
    }


}
