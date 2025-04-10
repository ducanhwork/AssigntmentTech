package com.utils;

public interface SQLCommand {
    interface Member {
        String FIND_ALL = "SELECT * FROM Member";
        String FIND_BY_ID = "SELECT * FROM Member WHERE id = ?";
        String INSERT_MEMBER = "INSERT INTO Member (Username, Password, Email, CreatedDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        String UPDATE_MEMBER = "UPDATE Member SET Firstname = ?, Lastname = ?, Phone = ?, Description = ?, UpdateTime = CURRENT_TIMESTAMP  WHERE id = ?";
        String DELETE_MEMBER = "DELETE FROM Member WHERE id = ?";
        String FIND_BY_EMAIL_AND_PASSWORD = "SELECT * FROM Member WHERE Email = ? AND Password = ?";
    }
    interface Content{
        String FIND_ALL = "SELECT * FROM Content";
        String FIND_BY_ID = "SELECT * FROM Content WHERE id = ?";
        String INSERT_CONTENT = "INSERT INTO Content (Title, Brief, Content, CreateDate, UpdateTime, AuthorId) VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        String UPDATE_CONTENT = "UPDATE Content SET Title = ?, Brief = ?, Content = ?, UpdateTime = CURRENT_TIMESTAMP WHERE id = ?";
        String DELETE_CONTENT = "DELETE FROM Content WHERE id = ?";
    }
}
