package com.parcial.user_service.controller;

import com.parcial.user_service.dto.CommentDTO;
import com.parcial.user_service.dto.Response;
import com.parcial.user_service.models.Comment;
import com.parcial.user_service.services.CommentService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Response<Comment>> save(@RequestBody CommentDTO commentDTO) {
        try {
            Comment savedComment = commentService.save(commentDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response<>("Comentario creado correctamente", savedComment));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(e.getMessage(), null));
        }
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<Response<List<CommentDTO>>> findByHostId(@PathVariable Integer hostId) {
        try {
            List<CommentDTO> comments = commentService.findByHostId(hostId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>("Comentarios obtenidos correctamente", comments));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(e.getMessage(), List.of()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteById(@PathVariable Long id) {
        try {
            commentService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>("Comentario eliminado correctamente", ""));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(e.getMessage(), null));
        }
    }

}
