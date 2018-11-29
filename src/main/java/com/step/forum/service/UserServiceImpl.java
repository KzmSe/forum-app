package com.step.forum.service;

import com.step.forum.dao.UserDao;
import com.step.forum.exception.DuplicateEmailException;
import com.step.forum.exception.InactiveStatusException;
import com.step.forum.exception.InvalidEmailException;
import com.step.forum.exception.InvalidPasswordException;
import com.step.forum.model.Action;
import com.step.forum.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) throws DuplicateEmailException, SQLException {
        userDao.addUser(user);
    }

    @Override
    public User login(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveStatusException, SQLException {
        return userDao.login(email, password);
    }

    @Override
    public List<Action> getActionListByRoleId(int id) throws SQLException {
        return userDao.getActionListByRoleId(id);
    }

}
