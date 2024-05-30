package com.parcial.hosting_service.clients;

import com.parcial.hosting_service.dto.CommentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Arrays;

@Service
public class CommentClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://gateway-service:8780/api/comments";

    public List<CommentDTO> findByHostId(Integer hostId) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("host", hostId.toString())
                .toUriString();
        CommentDTO[] commentsArray = restTemplate.getForObject(url, CommentDTO[].class);
        return commentsArray != null ? Arrays.asList(commentsArray) : List.of();
    }
}