package com.forum.admin.servlet;

import com.forum.common.constants.NavigationConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NavigationServlet", urlPatterns = "/ns")
public class NavigationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = null;
        String address = null;

        if (request.getParameter("action") != null){
            action = request.getParameter("action");
        }else {
            response.sendRedirect("/");
            return;
        }

        if (action.equals("index")){
            address = NavigationConstants.PAGE_ACTIVE_TOPICS;

        }else if (action.equals("pending-topics")){
            address = NavigationConstants.PAGE_PENDING_TOPICS;

        }else if (action.equals("active-users")){
            address = NavigationConstants.PAGE_ACTIVE_USERS;

        }else if (action.equals("blocked-users")){
            address = NavigationConstants.PAGE_BLOCKED_USERS;

        }else if (action.equals("login")){
            address = NavigationConstants.PAGE_LOGIN_COMMON;

        }else if (action.equals("inbox")){
            address = NavigationConstants.PAGE_INBOX;

        }else if (action.equals("mail-compose")){
            address = NavigationConstants.PAGE_MAIL_COMPOSE;

        }else if (action.equals("mail-view")){
            address = NavigationConstants.PAGE_MAIL_VIEW;

        }

        if (address != null){
            request.getRequestDispatcher(address).forward(request, response);
        }
    }
}