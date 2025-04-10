package com.entities;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {
    private Long id;
    private String title;
    private String brief;
    private String content;
    private LocalDateTime CreateDate;
    private LocalDateTime UpdateTime;
    private Long authorId;
}
