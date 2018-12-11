package com.forum.main.dao;

import com.forum.common.exceptions.UserCredentialsException;
import com.forum.main.model.Action;
import com.forum.main.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void addUser(User user) throws UserCredentialsException, SQLException;

    User login(String email, String password) throws UserCredentialsException, SQLException;

    List<Action> getActionListByRoleId(int id) throws SQLException;

}
