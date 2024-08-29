package com.bezkoder.springjwt.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionResponse {
    @JsonProperty
	private int questionId;
    @JsonProperty
    private String selectedOption;

    // Constructors
    public QuestionResponse() {
    }

	public QuestionResponse(int questionId, String selectedOption) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    // Getters and setters
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}