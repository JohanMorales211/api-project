package com.parcial.hosting_service.servicies;

import com.parcial.hosting_service.dto.FeatureDTO;
import com.parcial.hosting_service.models.Feature;

import java.util.List;

public interface FeatureService {

    public Feature save(FeatureDTO featureDTO);

    public List<Feature> findAll();

    public Feature update(FeatureDTO featureDTO);

    public Feature factory(FeatureDTO featureDTO);
}
