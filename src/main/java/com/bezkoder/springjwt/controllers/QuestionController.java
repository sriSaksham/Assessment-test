package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.List;

import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.payload.response.QuestionResponse;
import com.bezkoder.springjwt.service.ExcelUploadService;
import com.bezkoder.springjwt.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/*import org.springframework.web.bind.annotation.PathVariable;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuesService questionService;
    @GetMapping("/random")
    public ResponseEntity<List<Question>> getRandomQuestions() {
        List<Question> randomQuestions = questionService.getRandomQuestions(30);
        if (randomQuestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomQuestions);
    }

    
    @Autowired
    private ExcelUploadService excelUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty or not provided");
        }
        try {
        	System.out.println(file.getName()+ file.getSize());
            excelUploadService.uploadExcelFile(file);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    
    
    @PostMapping("/submit")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<QuestionResponse> responses) {
    	int score = questionService.calculateScore(responses);
    	
        questionService.saveUserScore(10, score);
        return ResponseEntity.ok(score);
    }

}
