package com.parcial.hosting_service.clients;

import com.parcial.hosting_service.dto.OriginDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OriginClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String ORIGIN_SERVICE_BASE_URL = "http://gateway-service:8780/api/origin";

    public OriginDTO getOriginByName(String name) {
        String url = UriComponentsBuilder.fromHttpUrl(ORIGIN_SERVICE_BASE_URL)
                .pathSegment(name)
                .toUriString();
        return restTemplate.getForObject(url, OriginDTO.class);
    }
}