package com.forum.main.servlet;

import com.forum.common.constants.NavigationConstants;
import com.forum.main.dao.TopicDaoImpl;
import com.forum.main.model.Topic;
import com.forum.main.service.TopicService;
import com.forum.main.service.TopicServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "")
public class HomeServlet extends HttpServlet {

    private TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Topic> topicList = new ArrayList<>();
        try {
            topicList = topicService.getAllTopic();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("topicList", topicList);

        String message = (String) request.getSession().getAttribute("message");

        if (message != null) {
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message");
        }

        request.getRequestDispatcher(NavigationConstants.PAGE_INDEX).forward(request, response);

    }
}