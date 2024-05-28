package com.eam.authentication_service.dto;

public record TokenResponseDTO (
        String access_token,
        int expires_in,
        int refresh_expires_in,
        String refresh_token,
        String token_type,
        String session_state,
        String scope
){

}
