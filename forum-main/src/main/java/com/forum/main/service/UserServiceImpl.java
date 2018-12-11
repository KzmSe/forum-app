package com.forum.main.service;

import com.forum.common.exceptions.UserCredentialsException;
import com.forum.main.dao.UserDao;
import com.forum.main.model.Action;
import com.forum.main.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) throws UserCredentialsException, SQLException {
        userDao.addUser(user);
    }

    @Override
    public User login(String email, String password) throws UserCredentialsException, SQLException {
        return userDao.login(email, password);
    }

    @Override
    public List<Action> getActionListByRoleId(int id) throws SQLException {
        return userDao.getActionListByRoleId(id);
    }

}