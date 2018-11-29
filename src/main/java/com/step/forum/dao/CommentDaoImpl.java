package com.step.forum.dao;

import com.step.forum.model.Comment;
import com.step.forum.model.User;
import com.step.forum.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private static final String ADD_COMMENT_SQL = "insert into comment(description, write_date, id_topic, id_user) values(?, ?, ?, ?)";
    private static final String GET_COMMENT_BY_TOPIC_ID_SQL = "select c.id_comment, c.description, c.write_date, u.id_user, u.email, u.first_name, u.last_name, u.image from comment c inner join user u on c.id_user=u.id_user where c.id_topic = ?";

    @Override
    public void addComment(Comment comment) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(ADD_COMMENT_SQL);
            ps.setString(1, comment.getDescription());
            ps.setString(2, comment.getWriteDate().toString());
            ps.setInt(3, comment.getTopic().getId());
            ps.setInt(4, comment.getUser().getId());
            ps.executeUpdate();

        } finally {
            DbUtil.closeAll(con, ps);
        }

    }

    @Override
    public List<Comment> getCommentsByTopicId(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comment> list = new ArrayList<>();

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_COMMENT_BY_TOPIC_ID_SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id_comment"));
                comment.setDescription(rs.getString("description"));
                comment.setWriteDate(rs.getTimestamp("write_date").toLocalDateTime());

                User user = new User();
                user.setId(rs.getInt("id_user"));
                user.setEmail(rs.getString("email"));
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setImagePath(rs.getString("image"));

                comment.setUser(user);
                list.add(comment);
            }

        }finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return list;
    }

}
