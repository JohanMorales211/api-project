package com.parcial.user_service.services.impl;

import com.parcial.user_service.dto.CommentDTO;
import com.parcial.user_service.exception.ClienteNoEncontradoException;
import com.parcial.user_service.models.Client;
import com.parcial.user_service.models.Comment;
import com.parcial.user_service.repositories.ClientRepository;
import com.parcial.user_service.repositories.CommentRepository;
import com.parcial.user_service.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final ClientRepository clientRepository;

    @Override
    public Comment save(CommentDTO commentDTO) {
        Optional<Client> client = clientRepository.findById(commentDTO.getClientId());
        if (client.isEmpty()) {
            throw new ClienteNoEncontradoException("Cliente no encontrado con ID: " + commentDTO.getClientId());
        }

        Comment comment = Comment.builder()
                .client(client.get())
                .hostId(commentDTO.getHostId())
                .content(commentDTO.getContent())
                .rating(commentDTO.getRating())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
