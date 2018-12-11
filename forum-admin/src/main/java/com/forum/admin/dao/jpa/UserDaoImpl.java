package com.forum.admin.dao.jpa;

import com.forum.admin.dao.UserDao;
import com.forum.admin.model.User;
import com.forum.admin.util.HibernateUtil;
import com.forum.common.constants.MessageConstants;
import com.forum.common.constants.TopicConstants;
import com.forum.common.constants.UserConstants;
import com.forum.common.exceptions.UserCredentialsException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User loginUser(String email, String password) throws UserCredentialsException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user;
        try{
            user = session.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();

            checkUserCredentials(user, password);

            Hibernate.initialize(user.getRole());

        } finally {
            HibernateUtil.commitTransaction(transaction);
        }

        return user;
    }

    @Override
    public List<User> getActiveOrBlockedUsers(int status) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<User> list = session.createQuery("from User u join fetch u.role where u.status = :status", User.class)
                .setParameter("status", status)
                .list();

        HibernateUtil.commitTransaction(transaction);
        return list;
    }

    @Override
    public boolean activateUser(int id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("update User set status = :status where id = :id")
                .setParameter("status", UserConstants.USER_STATUS_ACTIVE)
                .setParameter("id", id)
                .executeUpdate();

        HibernateUtil.commitTransaction(transaction);
        return true;
    }

    @Override
    public boolean blockUser(int id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("update User set status = :status where id = :id")
                .setParameter("status", UserConstants.USER_STATUS_BLOCKED)
                .setParameter("id", id)
                .executeUpdate();

        HibernateUtil.commitTransaction(transaction);
        return true;
    }

    private void checkUserCredentials(User user, String password) throws UserCredentialsException {
        String errorMessage = null;

        if (user == null) {
            errorMessage = MessageConstants.ERROR_MESSAGE_INVALID_EMAIL;

        } else if (!user.getPassword().equals(password)) {
            errorMessage = MessageConstants.ERROR_MESSAGE_INVALID_PASSWORD;

        } else if (user.getStatus() == UserConstants.USER_STATUS_INACTIVE) {
            errorMessage = MessageConstants.ERROR_MESSAGE_INACTIVE_ACCOUNT;
        }

        if (errorMessage != null) {
            throw new UserCredentialsException(errorMessage);
        }
    }

}