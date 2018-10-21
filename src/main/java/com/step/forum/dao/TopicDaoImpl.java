package com.step.forum.dao;

import com.step.forum.model.Topic;
import com.step.forum.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl implements TopicDao {

    private final String GET_ALL_TOPIC_SQL = "";

    @Override
    public List<Topic> getAllTopic() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Topic> list = new ArrayList<>();

        try {
            //TODO: jsjsjsjsjsjsjjsjsjsjsjsj

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

        }

    }
}
