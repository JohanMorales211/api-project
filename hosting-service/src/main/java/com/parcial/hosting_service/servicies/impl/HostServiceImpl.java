package com.parcial.hosting_service.servicies.impl;

import com.parcial.hosting_service.config.Constantes;
import com.parcial.hosting_service.dto.FeatureDTO;
import com.parcial.hosting_service.dto.HostDTO;
import com.parcial.hosting_service.dto.PictureDTO;
import com.parcial.hosting_service.models.Feature;
import com.parcial.hosting_service.models.Host;
import com.parcial.hosting_service.models.Picture;
import com.parcial.hosting_service.reposotories.HostRepository;
import com.parcial.hosting_service.servicies.FeatureService;
import com.parcial.hosting_service.servicies.HostService;
import com.parcial.hosting_service.servicies.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostServiceImpl implements HostService {

    @Autowired
    private final FeatureService featureService;
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private final HostRepository hostRepository;

    @Autowired
    private final PictureService pictureService;

    @Override
    public Host save(HostDTO hostDTO, FeatureDTO featureDTO, PictureDTO pictureDTO){
        Optional<Host> guardado = hostRepository.findByName(hostDTO.getName());

        if(guardado.isPresent()){
            throw new RuntimeException("El alojamiento con el nombre"+hostDTO.getName()+" ya existe");
        }
        Feature feature = featureService.save(featureDTO);
        Picture picture = pictureService.save(pictureDTO);
        return hostRepository.save(factory(hostDTO, feature, picture));


    }

    @Override
    public List<Host> findAll(){
        return hostRepository.findAll();
    }

    public Host findByName(String name){
        return hostRepository.findByName(name).orElse(null);
    }

    @Override
    public Host update(HostDTO hostDTO, Feature feature, Picture picture){
        return hostRepository.save( factory(hostDTO, feature, picture) );
    }

    @Override
    public Host factory(HostDTO hostDTO, Feature feature, Picture picture){
        Host nuevo = Host.builder()
                .name(hostDTO.getName())
                .rating(hostDTO.getRating())
                .price(hostDTO.getPrice())
                .maximumCapacity(hostDTO.getMaximumCapacity())
                .latitude(hostDTO.getLatitude())
                .longitude(hostDTO.getLongitude())
                .picture(picture)
                .feature(feature)
                .build();

        return nuevo;
    }
    private void validarNombreDestino(String nombreDestino){


        Object respuesta = rabbitTemplate.convertSendAndReceive(Constantes.EXCHANGE, Constantes.ROUTING_KEY, nombreDestino);


        if(Objects.isNull(respuesta)){
            throw new RuntimeException("Hubo un error recuperando la información del destino");
        }


        boolean existe = (Boolean) respuesta;


        if(!existe){
            throw new RuntimeException("El destino con el nomnre : "+nombreDestino+" no existe");
        }


    }


}
