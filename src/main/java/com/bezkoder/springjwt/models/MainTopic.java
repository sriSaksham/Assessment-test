package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MainTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long m_id;

    private String heading_name;

    @OneToMany(mappedBy = "mainTopic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TopicTable> topics;

    // Getters and setters

    public Long getM_id() {
        return m_id;
    }

    public void setM_id(Long m_id) {
        this.m_id = m_id;
    }

    public String getHeading_name() {
        return heading_name;
    }

    public void setHeading_name(String heading_name) {
        this.heading_name = heading_name;
    }

    public Set<TopicTable> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicTable> topics) {
        this.topics = topics;
    }
}
