package com.forum.main.service;

import com.forum.main.dao.TopicDao;
import com.forum.main.model.Topic;

import java.sql.SQLException;
import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao;

    public TopicServiceImpl(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public List<Topic> getAllTopic() throws SQLException {
        return topicDao.getAllTopic();
    }

    @Override
    public Topic getTopicById(int id) throws SQLException {
        return topicDao.getTopicById(id);
    }

    @Override
    public void incrementTopicViewCount(int id) throws SQLException {
        topicDao.incrementTopicViewCount(id);
    }

    @Override
    public void addTopic(Topic topic) throws SQLException {
        topicDao.addTopic(topic);
    }

    @Override
    public List<Topic> getPopularTopics() throws SQLException {
        return topicDao.getPopularTopics();
    }

    @Override
    public List<Topic> getAllTopicsByUserId(int id) throws SQLException {
        return topicDao.getAllTopicsByUserId(id);
    }

    @Override
    public List<Topic> getSimilarTopics(String[] keywords) throws SQLException {
        return topicDao.getSimilarTopics(keywords);
    }
}