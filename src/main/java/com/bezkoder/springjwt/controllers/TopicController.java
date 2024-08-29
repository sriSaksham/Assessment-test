package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.MainTopicResponseDto;
import com.bezkoder.springjwt.dto.QuestionDto;
import com.bezkoder.springjwt.dto.SubTopicResponseDto;
import com.bezkoder.springjwt.models.MainTopic;
import com.bezkoder.springjwt.service.MainTopicService;
import com.bezkoder.springjwt.service.QuestionService;
import com.bezkoder.springjwt.service.SubTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    private MainTopicService mainTopicService;

    @Autowired
    private SubTopicService subTopicService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/main-topic")
    public MainTopicResponseDto getMainTopicWithTopics(@RequestParam Long mid) {
        return mainTopicService.getMainTopicWithTopics(mid);
    }

    @GetMapping("/topic/all")
    public List<MainTopicResponseDto> getAllMainTopicsWithSubtopics() {
        return mainTopicService.getAllMainTopicsWithSubtopics();
    }

    @GetMapping("/sub-topics")
    public Set<SubTopicResponseDto> getSubTopicsByTid(@RequestParam Long tid) {
        return subTopicService.getSubTopicsByTid(tid);
    }

    @GetMapping("/questions")
    public Set<QuestionDto> getQuestionsBySubTopicId(@RequestParam Long subTopicId) {
        return questionService.getQuestionsBySubTopicId(subTopicId);
    }
}
