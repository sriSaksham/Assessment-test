package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.MainTopicResponseDto;
import com.bezkoder.springjwt.dto.TopicDto;
import com.bezkoder.springjwt.models.MainTopic;
import com.bezkoder.springjwt.models.SubTopic;
import com.bezkoder.springjwt.models.TopicTable;
import com.bezkoder.springjwt.repository.MainTopicRepository;
import com.bezkoder.springjwt.repository.TopicTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MainTopicService {

    @Autowired
    private MainTopicRepository mainTopicRepository;

    @Autowired
    private TopicTableRepository topicTableRepository;

    public MainTopicResponseDto getMainTopicWithTopics(Long mid) {
        MainTopic mainTopic = mainTopicRepository.findById(mid).orElse(null);
        if (mainTopic == null) {
            return null;
        }

        MainTopicResponseDto response = new MainTopicResponseDto();
        response.setMid(mainTopic.getM_id());
        response.setHeadingName(mainTopic.getHeading_name());

        Set<TopicDto> topicDtos = new HashSet<>();
        for (TopicTable topic : mainTopic.getTopics()) {
            TopicDto topicDto = new TopicDto();
            topicDto.setTid(topic.getTid());
            topicDto.setTopicName(topic.getTopicName());
            topicDtos.add(topicDto);
        }

        response.setTopics(topicDtos);
        return response;
    }

    public List<MainTopicResponseDto> getAllMainTopicsWithSubtopics() {
        return mainTopicRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private MainTopicResponseDto convertToDto(MainTopic mainTopic) {
        MainTopicResponseDto dto = new MainTopicResponseDto();
        dto.setMid(mainTopic.getM_id());
        dto.setHeadingName(mainTopic.getHeading_name());
        dto.setTopics(mainTopic.getTopics().stream().map(this::convertTopicToDto).collect(Collectors.toSet()));
        return dto;
    }

    private TopicDto convertTopicToDto(TopicTable topicTable) {
        TopicDto dto = new TopicDto();
        dto.setTid(topicTable.getTid());
        dto.setTopicName(topicTable.getTopicName());
        return dto;
    }
}
