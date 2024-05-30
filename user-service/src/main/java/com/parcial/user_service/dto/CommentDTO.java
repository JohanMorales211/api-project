package com.parcial.user_service.dto;

import com.parcial.user_service.models.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long clientId;
    private Integer hostId;
    private String content;
    private Integer rating;

    public CommentDTO(Comment comment) {
        this.clientId = comment.getClient().getId();
        this.hostId = comment.getHostId();
        this.content = comment.getContent();
        this.rating = comment.getRating();
    }
}