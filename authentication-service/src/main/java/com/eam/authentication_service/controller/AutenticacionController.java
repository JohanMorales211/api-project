package com.eam.authentication_service.controller;

import com.eam.authentication_service.dto.LoginDTO;
import com.eam.authentication_service.dto.Respuesta;
import com.eam.authentication_service.dto.TokenDTO;
import com.eam.authentication_service.service.LoginServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AutenticacionController {
    @Autowired
    LoginServicio loginServicio;

    @PostMapping("/signin")
    public ResponseEntity<Respuesta<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Login correcto", loginServicio.login(loginDTO)) );
    }


}
