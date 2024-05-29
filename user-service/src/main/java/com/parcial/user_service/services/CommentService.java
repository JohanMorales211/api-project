package com.parcial.user_service.services;

import com.parcial.user_service.dto.CommentDTO;
import com.parcial.user_service.models.Comment;

public interface CommentService {

    Comment save(CommentDTO commentDTO);

    void deleteById(Long id);
   
}