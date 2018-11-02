package com.step.forum.service;

import com.step.forum.dao.CommentDao;
import com.step.forum.model.Comment;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public List<Comment> getCommentsByTopicId(int id) {
        return commentDao.getCommentsByTopicId(id);
    }

}
