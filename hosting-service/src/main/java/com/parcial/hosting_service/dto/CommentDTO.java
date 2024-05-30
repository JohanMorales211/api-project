package com.parcial.hosting_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentDTO {
    private Long clientId;
    private Integer hostId;
    private String content;
    private Integer rating;
}
