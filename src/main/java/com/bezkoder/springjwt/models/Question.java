package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name  = "questions")
public class Question {
    @Id
    private int id;

    private String questionText;
    
    private String correctAnswer;
    
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

	@ManyToOne
	@JoinColumn(name = "sub_topic_id")
	private SubTopic subTopic;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    

    public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public SubTopic getSubTopic() {
		return subTopic;
	}

	public void setSubTopic(SubTopic subTopic) {
		this.subTopic = subTopic;
	}

	

    public Question(int id, String questionText, String correctAnswer, String optionA, String optionB, String optionC,
			String optionD, SubTopic subTopic) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.subTopic = subTopic;
	}

	

	public Question() {
		super();
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", correctAnswer=" + correctAnswer
				+ ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD
				+ ", subTopic=" + subTopic + "]";
	}

	
    
}
