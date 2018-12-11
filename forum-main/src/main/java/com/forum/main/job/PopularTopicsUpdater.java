package com.forum.main.job;

import com.forum.main.model.Topic;
import com.forum.main.service.TopicService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PopularTopicsUpdater {

    private List<Topic> popularTopics;
    private ScheduledExecutorService service;
    private TopicService topicService;

    //constructor
    public PopularTopicsUpdater(TopicService topicService) {
        this.topicService = topicService;
    }

    //getter
    public List<Topic> getPopularTopics() {
        return popularTopics;
    }

    //methods
    public void startJob() {
        updatePopularTopics();
    }

    public void stopJob() {
        if (service != null) {
            service.shutdown();
        }
    }

    private void updatePopularTopics() {
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            try {
                popularTopics = topicService.getPopularTopics();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, 0, 20, TimeUnit.SECONDS);
    }
}