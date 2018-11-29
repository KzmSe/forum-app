package com.step.forum.servlet;

import com.mysql.cj.xdevapi.JsonArray;
import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.NavigationConstants;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.job.PopularTopicsUpdater;
import com.step.forum.model.Topic;
import com.step.forum.model.User;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;
import com.step.forum.util.ValidationUtil;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "TopicServlet", urlPatterns = "/ts")
public class TopicServlet extends HttpServlet {

    private TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());
    private PopularTopicsUpdater updater;

    @Override
    public void init() throws ServletException {
        updater = new PopularTopicsUpdater(topicService);
        updater.startJob();
    }

    @Override
    public void destroy() {
        updater.stopJob();
    }

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

        if (action.equals(NavigationConstants.ACTION_ADD_TOPIC)) {
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

            if (!ValidationUtil.validate(title, description)) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_EMPTY_FIELDS);
                request.getRequestDispatcher(NavigationConstants.PAGE_NEW_TOPIC).forward(request, response);
            }

            try {
                topicService.addTopic(topic);
                request.getSession().setAttribute("message", MessageConstants.SUCCESS_MESSAGE_TOPIC_ADDED);

            } catch (SQLException e) {
                e.printStackTrace();
                request.getSession().setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
            }

            response.sendRedirect("/");

        } else if (action.equals(NavigationConstants.ACTION_GET_POPULAR_TOPICS)) {
            List<Topic> list = updater.getPopularTopics();
            JSONArray jsonArray = new JSONArray(list);
            response.setContentType("application/json");
            response.getWriter().write(jsonArray.toString());

        } else if (action.equals(NavigationConstants.ACTION_GET_ALL_ACTIVE_TOPICS)) {
            User user = (User) request.getSession().getAttribute("user");

            if (user != null) {
                int idUser = user.getId();
                List<Topic> allActiveTopics = new ArrayList<>();
                try {
                    allActiveTopics = topicService.getAllTopicsByUserId(idUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JSONArray jsonArray = new JSONArray(allActiveTopics);
                response.getWriter().write(jsonArray.toString());
            }
        } else if (action.equals(NavigationConstants.ACTION_GET_SIMILAR_TOPICS)) {
            String title = request.getParameter("title");
            String[] keywords = title.trim().split(" ");
            keywords = Arrays.stream(keywords).filter(keyword -> keyword.length() >= 3).toArray(keyword -> new String[keyword]);
            List<Topic> similarTopics = new ArrayList<>();
            try {
                similarTopics = (keywords.length == 0) ? new ArrayList<>() : topicService.getSimilarTopics(keywords);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (similarTopics.size() != 0) {
                request.setAttribute("similarTopics", similarTopics);
                request.getRequestDispatcher(NavigationConstants.FRAGMENT_SIMILAR_POST).forward(request, response);
            }
        }






    }
}