package com.step.forum.servlet;

import com.step.forum.constants.MessageConstants;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.model.Topic;
import com.step.forum.model.User;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "TopicServlet", urlPatterns = "/ts")
public class TopicServlet extends HttpServlet {

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

        if (action.equals("addTopic")) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            LocalDateTime shareDate = LocalDateTime.now();
            int viewCount = 0;

            User user = (User) request.getSession().getAttribute("user");

            //topic
            Topic topic = new Topic();
            topic.setTitle(title);
            topic.setDescription(description);
            topic.setShareDate(shareDate);
            topic.setViewCount(viewCount);
            topic.setUser(user);

            boolean resultAddTopic = topicService.addTopic(topic);

            if (!resultAddTopic) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
                request.getRequestDispatcher("/WEB-INF/view/new-topic.jsp").forward(request, response);
            }
        } else if (action.equals("getPopularTopics")) {
            List<Topic> list = topicService.getPopularTopics();
            //TODO: json-a cevirib page-right-menu ya gondermek..

        }

    }
}
