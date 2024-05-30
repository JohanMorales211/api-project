package com.parcial.user_service.services;

import java.util.List;

import com.parcial.user_service.dto.CommentDTO;
import com.parcial.user_service.models.Comment;

public interface CommentService {

    Comment save(CommentDTO commentDTO);

    void deleteById(Long id);

    List<CommentDTO> findByHostId(Integer hostId);

}