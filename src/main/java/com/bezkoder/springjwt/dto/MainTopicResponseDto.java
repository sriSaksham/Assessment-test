package com.bezkoder.springjwt.dto;

import java.util.Set;

public class MainTopicResponseDto {
    private Long mid;
    private String headingName;
    private Set<TopicDto> topics;

    // Getters and setters

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getHeadingName() {
        return headingName;
    }

    public void setHeadingName(String headingName) {
        this.headingName = headingName;
    }

    public Set<TopicDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicDto> topics) {
        this.topics = topics;
    }
}
