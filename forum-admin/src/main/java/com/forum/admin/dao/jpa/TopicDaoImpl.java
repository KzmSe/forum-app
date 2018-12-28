package com.forum.admin.dao.jpa;

import com.forum.admin.dao.TopicDao;
import com.forum.admin.model.Topic;
import com.forum.admin.util.HibernateUtil;
import com.forum.common.constants.TopicConstants;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl implements TopicDao {

    @Override
    public List<Topic> getActiveOrPendingTopics(int status) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Topic> list = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            list = session.createQuery("from Topic where status = :status")
                    .setParameter("status", status)
                    .list();

        } finally {
            HibernateUtil.commitTransaction(transaction);
        }

        return list;
    }

    @Override
    public boolean deleteTopic(int id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Topic topic = new Topic();
            topic.setId(id);
            session.delete(topic);

        } finally {
            HibernateUtil.commitTransaction(transaction);
        }

        return true;
    }

    @Override
    public boolean activateTopic(int id, String title, String desc) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.createQuery("update Topic set title = :title, description = :description, status = :status where id = :id")
                    .setParameter("title", title)
                    .setParameter("status", TopicConstants.TOPIC_STATUS_ACTIVE)
                    .setParameter("description", desc)
                    .setParameter("id", id)
                    .executeUpdate();

        } finally {
            HibernateUtil.commitTransaction(transaction);
        }

        return true;
    }

    @Override
    public Topic getTopicById(int id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        Topic topic = null;

        try {
            transaction = session.beginTransaction();
            topic = session.get(Topic.class, id);

        } finally {
            HibernateUtil.commitTransaction(transaction);
        }

        return topic;
    }
}