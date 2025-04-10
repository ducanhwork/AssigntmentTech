package com.services;

import com.entities.Member;
import com.utils.DatabaseConnectionUtil;
import com.utils.SQLCommand;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberServiceImpl implements MemberService {
    private Logger logger = Logger.getLogger(MemberServiceImpl.class.getName());
    @Override
    public Member findById(Long id) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM members WHERE id = ?";
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getLong("id"));
                member.setFirstname(resultSet.getString("firstname"));
                member.setLastname(resultSet.getString("lastname"));
                member.setUsername(resultSet.getString("username"));
                member.setPassword(resultSet.getString("password"));
                member.setPhone(resultSet.getString("phone"));
                member.setEmail(resultSet.getString("email"));
                member.setDescription(resultSet.getString("description"));
                member.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                member.setUpdateTime(resultSet.getTimestamp("update_time").toLocalDateTime());
                return member;
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getSQLState());
        }
        return null;
    }

    @Override
    public boolean insertMember(String username, String email, String password) {
        boolean result = false;
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            System.out.println("insertMember");
            String sql = SQLCommand.Member.INSERT_MEMBER;
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            System.out.println("insertMember 2");
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getSQLState());
        }
        return result;
    }

    @Override
    public boolean updateMember(Long id, String firstname, String lastname, String phone, String description) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            String sql = SQLCommand.Member.UPDATE_MEMBER;
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, description);
            preparedStatement.setLong(5, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getSQLState());
        }
        return false;

    }

    @Override
    public boolean deleteMember(Long id) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            String sql = SQLCommand.Member.DELETE_MEMBER;
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getSQLState());
        }
        return false;
    }
    @Override
    public List<Member> findAll (){
        List<Member> memberList = null;
        try(Connection connection = DatabaseConnectionUtil.getConnection()) {
            String sql = SQLCommand.Member.FIND_ALL;
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);
            memberList = new ArrayList<>();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getLong("id"));
                member.setFirstname(resultSet.getString("Firstname"));
                member.setLastname(resultSet.getString("Lastname"));
                member.setUsername(resultSet.getString("Username"));
                member.setPassword(resultSet.getString("Password"));
                member.setPhone(resultSet.getString("Phone"));
                member.setEmail(resultSet.getString("Email"));
                member.setDescription(resultSet.getString("Description"));
                member.setCreatedDate(resultSet.getTimestamp("CreatedDate").toLocalDateTime());
                member.setUpdateTime(resultSet.getTimestamp("UpdateTime").toLocalDateTime());
                memberList.add(member);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getSQLState());
        }
        return memberList;
    }

    @Override
    public Member findByEmailAndPassword(String email, String password) {
        try (Connection connection = DatabaseConnectionUtil.getConnection()) {
            String sql = SQLCommand.Member.FIND_BY_EMAIL_AND_PASSWORD;
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getLong("id"));
                member.setFirstname(resultSet.getString("Firstname"));
                member.setLastname(resultSet.getString("Lastname"));
                member.setUsername(resultSet.getString("Username"));
                member.setPassword(resultSet.getString("Password"));
                member.setPhone(resultSet.getString("Phone"));
                member.setEmail(resultSet.getString("Email"));
                member.setDescription(resultSet.getString("Description"));
                member.setCreatedDate(resultSet.getTimestamp("CreatedDate").toLocalDateTime());
                member.setUpdateTime(resultSet.getTimestamp("UpdateTime").toLocalDateTime());
                return member;
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getSQLState());
        }
        return null;
    }

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
     memberService.insertMember("ducanh","phanducanh@gmail.com","123");
    }
}
