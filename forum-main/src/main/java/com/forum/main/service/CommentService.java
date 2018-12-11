package com.forum.main.service;

import com.forum.main.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    void addComment(Comment comment) throws SQLException;

    List<Comment> getCommentsByTopicId(int id) throws SQLException;

}
