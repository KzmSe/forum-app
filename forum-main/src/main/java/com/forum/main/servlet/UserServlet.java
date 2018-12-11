package com.forum.main.servlet;

import com.forum.common.constants.MessageConstants;
import com.forum.common.constants.NavigationConstants;
import com.forum.common.constants.UserConstants;
import com.forum.common.exceptions.UserCredentialsException;
import com.forum.common.util.ConfigUtil;
import com.forum.common.util.CryptoUtil;
import com.forum.common.util.ValidationUtil;
import com.forum.main.dao.UserDaoImpl;
import com.forum.main.model.Role;
import com.forum.main.model.User;
import com.forum.main.service.UserService;
import com.forum.main.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(name = "UserServlet", urlPatterns = "/us")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5)
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

        if (action.equals(NavigationConstants.ACTION_DO_REGISTER)) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");

            if (!password.equals(rePassword)) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_PASSWORDS_MUST_BE_THE_SAME);
                request.getRequestDispatcher(NavigationConstants.PAGE_NEW_ACCOUNT).forward(request, response);
                return;
            }

            //validation
            boolean validationResult = ValidationUtil.validate(firstname, lastname, email, password);
            if (!validationResult) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_EMPTY_FIELDS);
                request.getRequestDispatcher(NavigationConstants.PAGE_NEW_ACCOUNT).forward(request, response);
            }

            //file
            Part image = request.getPart("image");
            Path pathToSaveDb = null;

            if (image.getSubmittedFileName().isEmpty()) {
                pathToSaveDb = Paths.get("default.png");

            } else {
                Path pathDirectories = Paths.get(ConfigUtil.getImageUploadPath(), email);
                Path pathFiles = Paths.get(pathDirectories.toString(), image.getSubmittedFileName());
                pathToSaveDb = Paths.get(email, image.getSubmittedFileName());

                if (!Files.exists(pathDirectories)) {
                    Files.createDirectories(pathDirectories);
                }

                Files.copy(image.getInputStream(), pathFiles, StandardCopyOption.REPLACE_EXISTING);
            }

            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setImagePath(pathToSaveDb.toString());
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

            try {
                userService.addUser(user);
                String body = "Qeydiyyati tamamlamaq ucun linke daxil olun:" + "http://localhost:8080/us?action=activate&token=" + user.getToken();

                //paralel thread for mail sending
                ExecutorService service = null;
                try {
                    service = Executors.newFixedThreadPool(20);
                    service.submit(() -> {
                        //TODO: mail gonder..
//                            EmailUtil.sendEmail(email, "REGISTRATION", body);
                    });

                } finally {
                    if (service != null) {
                        service.shutdown();
                    }
                }

                request.setAttribute("message", MessageConstants.SUCCESS_MESSAGE_REGISTRATION);
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN_COMMON).forward(request, response);

            } catch (UserCredentialsException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_NEW_ACCOUNT).forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
                request.getRequestDispatcher(NavigationConstants.PAGE_NEW_ACCOUNT).forward(request, response);
            }

        } else if (action.equals(NavigationConstants.ACTION_DO_LOGIN)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (!ValidationUtil.validate(email, password)) {
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_EMPTY_FIELDS);
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN_COMMON).forward(request, response);
            }

            try {
                User user = null;
                user = userService.login(email, CryptoUtil.inputToHash(password));

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("/");
                }

            } catch (UserCredentialsException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN_COMMON).forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL_ERROR);
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN_COMMON).forward(request, response);
            }
        } else if (action.equals(NavigationConstants.ACTION_ACTIVATE)) {
            //TODO: bura yazilmalidir..
        }


    }
}