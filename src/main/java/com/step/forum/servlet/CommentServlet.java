package com.step.forum.servlet;

import com.step.forum.constants.MessageConstants;
import com.step.forum.dao.CommentDaoImpl;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.model.Comment;
import com.step.forum.model.Topic;
import com.step.forum.model.User;
import com.step.forum.service.CommentService;
import com.step.forum.service.CommentServiceImpl;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
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

        if (action.equals("addComment")) {
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

            boolean result = commentService.addComment(comment);
            if (result) {
                request.setAttribute("message", MessageConstants.SUCCESS_MESSAGE_COMMENT_ADDED);

            } else {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
            }

            Topic topic1 = topicService.getTopicById(idTopic);
            request.setAttribute("topic", topic1);

            request.getRequestDispatcher("/WEB-INF/view/topic.jsp").forward(request, response);

        } else if (action.equals("getComments")) {
            int idTopic = Integer.parseInt(request.getParameter("id"));
            List<Comment> comments = commentService.getCommentsByTopicId(idTopic);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/WEB-INF/fragments/page-comment.jsp").forward(request, response);

        }

    }
}