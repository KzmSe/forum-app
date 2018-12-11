package com.forum.main.servlet;

import com.forum.common.constants.MessageConstants;
import com.forum.common.constants.NavigationConstants;
import com.forum.common.util.ValidationUtil;
import com.forum.main.dao.CommentDaoImpl;
import com.forum.main.dao.TopicDaoImpl;
import com.forum.main.model.Comment;
import com.forum.main.model.Topic;
import com.forum.main.model.User;
import com.forum.main.service.CommentService;
import com.forum.main.service.CommentServiceImpl;
import com.forum.main.service.TopicService;
import com.forum.main.service.TopicServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CommentServlet", urlPatterns = "/cs")
public class CommentServlet extends HttpServlet {

    private CommentService commentService = new CommentServiceImpl(new CommentDaoImpl());
    private TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        } else {
            response.sendRedirect("/");
            return;
        }

        if (action.equals(NavigationConstants.ACTION_ADD_COMMENT)) {
            String description = request.getParameter("description");
            LocalDateTime writeDate = LocalDateTime.now();
            //get id user
            User user = (User) request.getSession().getAttribute("user");
            int idUser = user.getId();

            //get id topic
            int idTopic = Integer.parseInt(request.getParameter("idTopic"));

            Comment comment = new Comment();
            comment.setDescription(description);
            comment.setWriteDate(writeDate);

            Topic topic = new Topic();
            topic.setId(idTopic);
            comment.setTopic(topic);

            User user1 = new User();
            user1.setId(idUser);
            comment.setUser(user1);

            Topic topic1 = null;
            try {
                topic1 = topicService.getTopicById(idTopic);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("topic", topic1);

            if (!ValidationUtil.validate(description)) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_EMPTY_FIELDS);
                request.getRequestDispatcher(NavigationConstants.PAGE_TOPIC).forward(request, response);
                return;
            }

            try {
                commentService.addComment(comment);
                request.setAttribute("message", MessageConstants.SUCCESS_MESSAGE_COMMENT_ADDED);

            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
            }

            request.getRequestDispatcher(NavigationConstants.PAGE_TOPIC).forward(request, response);

        } else if (action.equals(NavigationConstants.ACTION_GET_COMMENTS)) {
            int idTopic = Integer.parseInt(request.getParameter("id"));
            List<Comment> comments;
            try {
                comments = commentService.getCommentsByTopicId(idTopic);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException();
            }
            request.setAttribute("comments", comments);
            request.getRequestDispatcher(NavigationConstants.FRAGMENT_COMMENTS).forward(request, response);

        }

    }
}