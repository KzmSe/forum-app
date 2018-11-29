package com.step.forum.service;

import com.step.forum.exception.DuplicateEmailException;
import com.step.forum.exception.InactiveStatusException;
import com.step.forum.exception.InvalidEmailException;
import com.step.forum.exception.InvalidPasswordException;
import com.step.forum.model.Action;
import com.step.forum.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void addUser(User user) throws DuplicateEmailException, SQLException;

    User login(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveStatusException, SQLException;

    List<Action> getActionListByRoleId(int id) throws SQLException;

}
