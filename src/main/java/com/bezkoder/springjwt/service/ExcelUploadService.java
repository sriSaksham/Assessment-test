package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.repository.QuestionRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class ExcelUploadService {

    @Autowired
    private QuestionRepository questionRepository;
    
    public void uploadExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getSheetName()+ " :sheetnames");
        Iterator<Row> rowIterator = sheet.iterator();
        List<Question> questions = new ArrayList<>();

        while (rowIterator.hasNext()) {
        	System.out.println("in the while");
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
            	
                // Skip header row
                continue;
            }

            // Assuming your Excel sheet columns are structured accordingly
            int id = (int) row.getCell(0).getNumericCellValue();
            String questionText = row.getCell(1).getStringCellValue().trim();
            String optionA = row.getCell(2).getStringCellValue().trim();
            String optionB = row.getCell(3).getStringCellValue().trim();
            String optionC = row.getCell(4).getStringCellValue().trim();
            String optionD = row.getCell(5).getStringCellValue().trim();
            String correctAnswer = row.getCell(6).getStringCellValue().trim();

            Question question = new Question();
            question.setId(id);
            question.setQuestionText(questionText);
            question.setOptionA(optionA);
            question.setOptionB(optionB);
            question.setOptionC(optionC);
            question.setOptionD(optionD);
            question.setCorrectAnswer(correctAnswer);

            questions.add(question);
        }

        workbook.close();
        System.out.println(questions.size());

        // Save all questions to the database
        questionRepository.saveAll(questions);
    }
}