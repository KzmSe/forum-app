package com.step.forum.dao;

import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.UserConstants;
import com.step.forum.exception.DuplicateEmailException;
import com.step.forum.exception.InactiveStatusException;
import com.step.forum.exception.InvalidEmailException;
import com.step.forum.exception.InvalidPasswordException;
import com.step.forum.model.Role;
import com.step.forum.model.User;
import com.step.forum.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private final String ADD_USER_SQL = "insert into user(email, password, token, status, id_role, first_name, last_name) values(?, ?, ?, ?, ?, ?, ?)";
    private final String GET_EMAIL_COUNT_SQL = "select count(email) as count from user where email = ?";
    private final String GET_USER_BY_EMAIL_SQL = "select * from user where email = ?";

    @Override
    public boolean addUser(User user) throws DuplicateEmailException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            if (!isEmailValid(user.getEmail())) {
                throw new DuplicateEmailException(MessageConstants.ERROR_MESSAGE_DUPLICATE_EMAIL);
            }

            con = DbUtil.getConnection();
            ps = con.prepareStatement(ADD_USER_SQL);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getToken());
            ps.setInt(4, user.getStatus());
            ps.setInt(5, user.getRole().getId());
            ps.setString(6, user.getFirstname());
            ps.setString(7, user.getLastname());

            ps.executeUpdate();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DbUtil.closeAll(con, ps);
        }

        return result;
    }

    @Override
    public User login(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveStatusException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_USER_BY_EMAIL_SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {

                if (!rs.getString("password").equals(password)) {
                    throw new InvalidPasswordException(MessageConstants.ERROR_MESSAGE_INVALID_PASSWORD);
                }

                if (rs.getInt("status") == UserConstants.USER_STATUS_INACTIVE) {
                    throw new InactiveStatusException(MessageConstants.ERROR_MESSAGE_INACTIVE_STATUS);
                }

                user = new User();
                user.setId(rs.getInt("id_user"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setToken(rs.getString("token"));
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));

                Role role = new Role();
                role.setId(rs.getInt("id_role"));
                user.setRole(role);

            } else {
                throw new InvalidEmailException(MessageConstants.ERROR_MESSAGE_INVALID_EMAIL);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DbUtil.closeAll(con, ps);
        }

        return user;
    }




    //private methods
    private boolean isEmailValid(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_EMAIL_COUNT_SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getInt("count") == 0) {
                    result = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DbUtil.closeAll(con, ps);
        }

        return result;
    }


}
