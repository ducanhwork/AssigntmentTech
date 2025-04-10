CREATE DATABASE content_management;
use content_management;
CREATE TABLE Member (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        Firstname VARCHAR(100),
                        Lastname VARCHAR(100),
                        Username VARCHAR(100) NOT NULL UNIQUE,
                        Password VARCHAR(255) NOT NULL,
                        Phone VARCHAR(20),
                        Email VARCHAR(100),
                        Description TEXT,
                        CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                        UpdateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Content (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         Title VARCHAR(255) NOT NULL,
                         Brief TEXT NOT NULL,
                         Content TEXT NOT NULL,
                         CreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                         UpdateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         AuthorId INT NOT NULL,
                         FOREIGN KEY (AuthorId) REFERENCES Member(id)
);
