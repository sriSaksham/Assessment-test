package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.QuestionDto;
import com.bezkoder.springjwt.dto.SubTopicResponseDto;
import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.models.SubTopic;
import com.bezkoder.springjwt.repository.SubTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubTopicService {

    @Autowired
    private SubTopicRepository subTopicRepository;

    public Set<SubTopicResponseDto> getSubTopicsByTid(Long tid) {
        Set<SubTopic> subTopics = subTopicRepository.findByTopicTable_Tid(tid);
        Set<SubTopicResponseDto> responseDtos = new HashSet<>();

        for (SubTopic subTopic : subTopics) {
            SubTopicResponseDto dto = new SubTopicResponseDto();
            dto.setSubTopicId(subTopic.getSubTopicId());
            dto.setSubTopicName(subTopic.getSubTopicName());

            Set<QuestionDto> questionDtos = new HashSet<>();
            for (Question question : subTopic.getQuestions()) {
                QuestionDto questionDto = new QuestionDto();
                questionDto.setQuestionId(question.getId());
                questionDto.setQuestionText(question.getQuestionText());
                questionDto.setCorrectAnswer(question.getCorrectAnswer());
                questionDto.setOptionA(question.getOptionA());
                questionDto.setOptionB(question.getOptionB());
                questionDto.setOptionC(question.getOptionC());
                questionDto.setOptionD(question.getOptionD());
                questionDtos.add(questionDto);
            }

            dto.setQuestions(questionDtos);
            responseDtos.add(dto);
        }

        return responseDtos;
    }
}
