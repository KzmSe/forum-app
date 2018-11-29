package com.step.forum.dao;

import com.step.forum.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {

    void addComment(Comment comment) throws SQLException;

    List<Comment> getCommentsByTopicId(int id) throws SQLException;

}