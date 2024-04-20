package com.parcial.hosting_service.controller;

import com.parcial.hosting_service.dto.RequestDTO;
import com.parcial.hosting_service.dto.Response;
import com.parcial.hosting_service.models.Host;
import com.parcial.hosting_service.servicies.HostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostings")
@AllArgsConstructor
public class HostController {

    @Autowired
    private final HostService hostService;

    @PostMapping
    public ResponseEntity<Response<Host>> save(@RequestBody RequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Response<>("Alojamiento creado correctamente", hostService.save(requestDTO.getHostDTO(), requestDTO.getFeatureDTO(), requestDTO.getPictureDTO())) );
    }

    @GetMapping
    public ResponseEntity<Response<List<Host>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>("", hostService.findAll()) );
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<Host>> findAll(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>("", hostService.findByName(name)) );
    }

}
