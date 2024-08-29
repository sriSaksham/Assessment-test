package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.QuestionDto;
import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Set<QuestionDto> getQuestionsBySubTopicId(Long subTopicId) {
        Set<Question> questions = questionRepository.findBySubTopic_SubTopicId(subTopicId);
        Set<QuestionDto> responseDto = new HashSet<>();

        for (Question question : questions) {
            QuestionDto dto = new QuestionDto();
            dto.setQuestionId(question.getId());
            dto.setQuestionText(question.getQuestionText());
            dto.setCorrectAnswer(question.getCorrectAnswer());
            dto.setOptionA(question.getOptionA());
            dto.setOptionB(question.getOptionB());
            dto.setOptionC(question.getOptionC());
            dto.setOptionD(question.getOptionD());
            responseDto.add(dto);
        }

        return responseDto;
    }
}
