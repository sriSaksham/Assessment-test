package com.bezkoder.springjwt.dto;

public class TopicDto {
    private Long tid;
    private String topicName;

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
}
