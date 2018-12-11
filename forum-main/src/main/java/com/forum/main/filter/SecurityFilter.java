package com.forum.main.filter;

import com.forum.common.constants.UserConstants;
import com.forum.main.dao.UserDaoImpl;
import com.forum.main.model.Action;
import com.forum.main.model.User;
import com.forum.main.service.UserService;
import com.forum.main.service.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    private UserService userService = new UserServiceImpl(new UserDaoImpl());
    private List<Action> actionListAuth = new ArrayList<>();
    private List<Action> actionListNonAuth = new ArrayList<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");
        boolean isAllowed = false;

        if (action == null) {
            chain.doFilter(req, resp);
            return;
        }

        List<Action> list = (user == null) ? actionListNonAuth : actionListAuth;
        isAllowed = list.stream().anyMatch(a -> a.getActionType().equals(action));

        if (isAllowed) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect("/");
        }

    }

    public void init(FilterConfig config) throws ServletException {
        try {
            actionListAuth = userService.getActionListByRoleId(UserConstants.USER_ROLE_USER);
            actionListNonAuth = userService.getActionListByRoleId(UserConstants.USER_ROLE_UNAUTHENTICATED);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}