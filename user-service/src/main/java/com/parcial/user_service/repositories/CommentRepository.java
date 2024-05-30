package com.parcial.user_service.repositories;

import com.parcial.user_service.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByClientId(Long clientId);

    List<Comment> findByHostId(Integer hostId);
}