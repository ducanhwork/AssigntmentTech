package com.services;

import com.entities.Content;
import com.utils.DatabaseConnectionUtil;
import com.utils.SQLCommand;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentServiceImpl implements ContentService {
    private static final Logger logger = Logger.getLogger(ContentServiceImpl.class.getName());
    @Override
    public List<Content> findAll() {
        List<Content> listContents = new ArrayList<>();
        try (

                var connection = DatabaseConnectionUtil.getConnection();
                var preparedStatement = connection.prepareStatement(SQLCommand.Content.FIND_ALL);
                var resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Content content = new Content();
                content.setId(resultSet.getLong("id"));
                content.setTitle(resultSet.getString("Title"));
                content.setBrief(resultSet.getString("Brief"));
                content.setContent(resultSet.getString("Content"));
                content.setAuthorId(resultSet.getLong("AuthorId"));
                content.setCreateDate(resultSet.getTimestamp("CreateDate").toLocalDateTime());
                content.setUpdateTime(resultSet.getTimestamp("UpdateTime").toLocalDateTime());
                listContents.add(content);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error while fetching contents: " + e.getMessage());
        }
        return listContents;
    }

    @Override
    public Content findById(Long id) {
        try {
            var connection = DatabaseConnectionUtil.getConnection();
            var preparedStatement = connection.prepareStatement(SQLCommand.Content.FIND_BY_ID);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Content content = new Content();
                content.setId(resultSet.getLong("id"));
                content.setTitle(resultSet.getString("Title"));
                content.setBrief(resultSet.getString("Brief"));
                content.setContent(resultSet.getString("Content"));
                content.setAuthorId(resultSet.getLong("AuthorId"));
                content.setCreateDate(resultSet.getTimestamp("CreateDate").toLocalDateTime());
                content.setUpdateTime(resultSet.getTimestamp("UpdateTime").toLocalDateTime());
                return content;
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while fetching content by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insertContent(String title, String brief, String content, Long authorId) {
        try (
                var connection = DatabaseConnectionUtil.getConnection();
                var preparedStatement = connection.prepareStatement(SQLCommand.Content.INSERT_CONTENT)
        ) {
            logger.log(Level.INFO, "Inserting content with title: " + title);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, brief);
            preparedStatement.setString(3, content);
            preparedStatement.setLong(4, authorId);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error while inserting content: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateContent(Long id, String title, String brief, String content) {
        try(
                var connection = DatabaseConnectionUtil.getConnection();
                var preparedStatement = connection.prepareStatement(SQLCommand.Content.UPDATE_CONTENT)
                ) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, brief);
            preparedStatement.setString(3, content);
            preparedStatement.setLong(4, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while updating content: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteContent(Long id) {
        try (
                var connection = DatabaseConnectionUtil.getConnection();
                var preparedStatement = connection.prepareStatement(SQLCommand.Content.DELETE_CONTENT)
        ) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while deleting content: " + e.getMessage());
        }
        return false;
    }
}
