package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TopicTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    private String topicName;

    @ManyToOne
    @JoinColumn(name = "mid")
    private MainTopic mainTopic;

    @OneToMany(mappedBy = "topicTable")
    private Set<SubTopic> subTopics;

    // Getters and setters

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public MainTopic getMainTopic() {
        return mainTopic;
    }

    public void setMainTest(MainTopic mainTopic) {
        this.mainTopic = mainTopic;
    }

    public Set<SubTopic> getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(Set<SubTopic> subTopics) {
        this.subTopics = subTopics;
    }
}
