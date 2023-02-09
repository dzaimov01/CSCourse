package com.cscoursesdz.cscoursesdz.dto;

import com.cscoursesdz.cscoursesdz.models.Comment;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private UUID id;
    private String userName;
    private String body;
    private UUID postId;
    private UUID userId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
