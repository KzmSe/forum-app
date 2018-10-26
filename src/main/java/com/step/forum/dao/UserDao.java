package com.step.forum.dao;

import com.step.forum.exception.DuplicateEmailException;
import com.step.forum.exception.InactiveStatusException;
import com.step.forum.exception.InvalidEmailException;
import com.step.forum.exception.InvalidPasswordException;
import com.step.forum.model.User;

public interface UserDao {

    boolean addUser(User user) throws DuplicateEmailException;

    User login(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveStatusException;

}
