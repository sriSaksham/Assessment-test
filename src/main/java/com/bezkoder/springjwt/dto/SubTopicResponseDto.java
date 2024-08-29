package com.bezkoder.springjwt.dto;

import java.util.Set;

public class SubTopicResponseDto {

    private Long subTopicId;
    private String subTopicName;
    private Set<QuestionDto> questions;

    // Getters and setters

    public Long getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(Long subTopicId) {
        this.subTopicId = subTopicId;
    }

    public String getSubTopicName() {
        return subTopicName;
    }

    public void setSubTopicName(String subTopicName) {
        this.subTopicName = subTopicName;
    }

    public Set<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDto> questions) {
        this.questions = questions;
    }
}
