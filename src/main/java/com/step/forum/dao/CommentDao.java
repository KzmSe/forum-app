package com.step.forum.dao;

import com.step.forum.model.Comment;

import java.util.List;

public interface CommentDao {

    boolean addComment(Comment comment);

    List<Comment> getCommentsByTopicId(int id);

}
