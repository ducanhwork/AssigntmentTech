package com.services;

import com.entities.Content;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentService {
    List<Content> findAll();

    Content findById(Long id);

    boolean insertContent(String title, String brief, String content, Long authorId);

    boolean updateContent(Long id, String title, String brief, String content);

    boolean deleteContent(Long id);
}
