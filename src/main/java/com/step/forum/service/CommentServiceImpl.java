package com.step.forum.service;

import com.step.forum.dao.CommentDao;
import com.step.forum.model.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(Comment comment) throws SQLException {
        commentDao.addComment(comment);
    }

    @Override
    public List<Comment> getCommentsByTopicId(int id) throws SQLException {
        return commentDao.getCommentsByTopicId(id);
    }

}
