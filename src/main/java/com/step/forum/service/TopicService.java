package com.step.forum.service;

import com.step.forum.model.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> getAllTopic();

    Topic getTopicById(int id);

    boolean incrementTopicViewCount(int id);

    boolean addTopic(Topic topic);

    List<Topic> getPopularTopics();

    List<Topic> getAllTopicsByUserId(int id);

}
