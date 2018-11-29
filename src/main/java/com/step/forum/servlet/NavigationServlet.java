package com.step.forum.servlet;

import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.NavigationConstants;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.model.Topic;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "NavigationServlet", urlPatterns = "/ns")
public class NavigationServlet extends HttpServlet {

    private TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = null;
        String address = null;

        //action
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        } else {
            response.sendRedirect("/");
            return;
        }

        //address
        if (action.equals(NavigationConstants.ACTION_NEW_TOPIC)) {
            address = NavigationConstants.PAGE_NEW_TOPIC;

        } else if (action.equals(NavigationConstants.ACTION_TOPIC)) {
            int idTopic = Integer.parseInt(request.getParameter("id"));
            Topic topic = null;
            try {
                topic = topicService.getTopicById(idTopic);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (topic != null) {
                try {
                    topicService.incrementTopicViewCount(idTopic);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("topic", topic);
                address = NavigationConstants.PAGE_TOPIC;
            } else {
                response.sendRedirect("/");
                return;
            }

        } else if (action.equals(NavigationConstants.ACTION_REGISTER)) {
            address = NavigationConstants.PAGE_NEW_ACCOUNT;

        } else if (action.equals(NavigationConstants.ACTION_LOGIN)) {
            address = NavigationConstants.PAGE_LOGIN;

        } else if (action.equals(NavigationConstants.ACTION_LOGOUT)) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("/");
        }


        if (address != null) {
            request.getRequestDispatcher(address).forward(request, response);
        }
    }





}
