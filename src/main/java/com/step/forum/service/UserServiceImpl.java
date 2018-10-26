package com.step.forum.service;

import com.step.forum.dao.UserDao;
import com.step.forum.exception.DuplicateEmailException;
import com.step.forum.exception.InactiveStatusException;
import com.step.forum.exception.InvalidEmailException;
import com.step.forum.exception.InvalidPasswordException;
import com.step.forum.model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean addUser(User user) throws DuplicateEmailException{
        return userDao.addUser(user);
    }

    @Override
    public User login(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveStatusException {
        return userDao.login(email, password);
    }

}
