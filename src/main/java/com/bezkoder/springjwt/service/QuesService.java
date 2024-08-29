package com.bezkoder.springjwt.service;
import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.payload.response.QuestionResponse;
import com.bezkoder.springjwt.repository.PassedStudentRepository;
import com.bezkoder.springjwt.repository.QuestionRepository;
import com.bezkoder.springjwt.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuesService {

	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> getRandomQuestions(int numberOfQuestions) {
		List<Question> questions = questionRepository.findAll();
		if (questions.size() <= numberOfQuestions) {
			return questions; // Return all questions if there are fewer than the requested number
		}
		Collections.shuffle(questions);
		return questions.stream().limit(numberOfQuestions).collect(Collectors.toList());
	}
	
	public int calculateScore(List<QuestionResponse> responses) {
        int score = 0;
        for (QuestionResponse response : responses) {
            int questionId = response.getQuestionId();
            String selectedOption = response.getSelectedOption();
            String correctOption = findCorrectAnswer(questionId);
System.out.println("hello sb" +selectedOption +"  corr  "+ correctOption+"  "+questionId);
            if (correctOption != null && correctOption.equals(selectedOption)) {
            	System.out.println("hi");
                score++;
            }
        }
        return score;
    }
	
	 
	private String findCorrectAnswer(int quesId) {
	Optional<Question> questions = questionRepository.findById(quesId);
	//System.out.println("Question From DB "+questions.get());
       
            if (questions.isPresent()) {
                return questions.get().getCorrectAnswer();
                
            }
            else {
        
        return "0";
            }
        
    }
	
	 @Autowired
	  private UserScoreRepository userRepository;

	@Autowired
	private PassedStudentRepository passedStudentRepository;

	@Autowired
	private UserScoreRepository userScoreRepository;

	User user;

	    public void saveUserScore(long userId, int score) {
	        UserScore userScore = new UserScore();
	        userScore.setUserId(user.getId());
	        userScore.setScore(score);
	        userScore.setCreatedAt(new Date());

	        userScoreRepository.save(userScore);

			if (score > 20) {
				PassedStudent passedStudent = new PassedStudent();
				passedStudent.setUser(user.getId());
				passedStudent.setScore(score);
				passedStudent.setEmail(user.getEmail());
				passedStudent.setUsername(user.getUsername());
				passedStudent.setCreatedAt(new Date());

				passedStudentRepository.save(passedStudent);
			}
	    }
}
