package com.step.forum.servlet;

import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.UserConstants;
import com.step.forum.dao.UserDaoImpl;
import com.step.forum.exception.DuplicateEmailException;
import com.step.forum.exception.InactiveStatusException;
import com.step.forum.exception.InvalidEmailException;
import com.step.forum.exception.InvalidPasswordException;
import com.step.forum.model.Role;
import com.step.forum.model.User;
import com.step.forum.service.UserService;
import com.step.forum.service.UserServiceImpl;
import com.step.forum.util.CryptoUtil;
import com.step.forum.util.EmailUtil;
import com.step.forum.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

@WebServlet(name = "UserServlet", urlPatterns = "/us")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl(new UserDaoImpl());

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
        }

        if (action.equals("doRegister")) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");

            if (!password.equals(rePassword)) {
                response.sendRedirect("/ns?action=register");
                return;
            }

            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(CryptoUtil.inputToHash(password));

            //status
            user.setStatus(UserConstants.USER_STATUS_INACTIVE);

            //role
            Role role = new Role();
            role.setId(UserConstants.USER_ROLE_USER);
            user.setRole(role);

            //token
            UUID token = UUID.randomUUID();
            user.setToken(token.toString());

            //validation
            boolean validationResult = ValidationUtil.validate(firstname, lastname, email, password);
            if (!validationResult) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_EMPTY_FIELDS);
                request.getRequestDispatcher("/WEB-INF/view/new-account.jsp").forward(request, response);
            }

            try {
                if (userService.addUser(user)) {
                    String body = "Qeydiyyati tamamlamaq ucun linke daxil olun:" + "http://localhost:8080/us?action=activate&token=" + user.getToken();

                    //paralel thread for mail sending
                    ExecutorService service = null;
                    try {
                        service = Executors.newFixedThreadPool(20);
                        service.submit(() -> {
                            EmailUtil.sendEmail(email, "REGISTRATION", body);
                        });

                    } finally {
                        if (service != null) {
                            service.shutdown();
                        }
                    }

                    request.setAttribute("message", MessageConstants.SUCCESS_MESSAGE_REGISTRATION);
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
                    request.getRequestDispatcher("/WEB-INF/view/new-account.jsp").forward(request, response);
                }

            } catch (DuplicateEmailException e) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_DUPLICATE_EMAIL);
                request.getRequestDispatcher("/WEB-INF/view/new-account.jsp").forward(request, response);
            }

        } else if (action.equals("doLogin")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (!ValidationUtil.validate(email, password)) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_EMPTY_FIELDS);
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }

            try {
                //TODO: set hash..
                User user = userService.login(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("/");
                }

            } catch (InvalidEmailException | InvalidPasswordException | InactiveStatusException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        } else if (action.equals("activate")) {
            //TODO: bura yazilmalidir..

        }









    }
}
