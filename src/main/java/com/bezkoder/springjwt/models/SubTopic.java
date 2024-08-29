package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class SubTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subTopicId;

    @Column(name = "sub_topic_name")
    private String subTopicName;

    @ManyToOne
    @JoinColumn(name = "tid")
    private TopicTable topicTable;

    @OneToMany(mappedBy = "subTopic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions;

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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public TopicTable getTopicTable() {
        return topicTable;
    }

    public void setTopicTable(TopicTable topicTable) {
        this.topicTable = topicTable;
    }
}
