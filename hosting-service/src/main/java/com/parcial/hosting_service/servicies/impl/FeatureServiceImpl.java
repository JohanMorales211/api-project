package com.parcial.hosting_service.servicies.impl;

import com.parcial.hosting_service.dto.FeatureDTO;
import com.parcial.hosting_service.models.Feature;
import com.parcial.hosting_service.reposotories.FeatureRepository;
import com.parcial.hosting_service.reposotories.HostRepository;
import com.parcial.hosting_service.servicies.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private final FeatureRepository featureRepository;

    @Autowired
    private final HostRepository hostRepository;

    @Override
    public Feature save(FeatureDTO featureDTO){

        return featureRepository.save(factory(featureDTO));
    }

    @Override
    public List<Feature> findAll(){
        return featureRepository.findAll();
    }

    @Override
    public Feature update(FeatureDTO featureDTO){
        return featureRepository.save( factory(featureDTO) );
    }

    @Override
    public Feature factory(FeatureDTO featureDTO){
        Feature nuevo = Feature.builder()
            .hasSwimmingPool(featureDTO.getHasSwimmingPool())
            .hasBuffet(featureDTO.getHasBuffet())
            .hasWifi(featureDTO.getHasBuffet())
            .hasFridge(featureDTO.getHasFridge())
            .build();

        return nuevo;
    }
}
