package com.parcial.hosting_service.servicies;

import com.parcial.hosting_service.dto.FeatureDTO;
import com.parcial.hosting_service.dto.HostDTO;
import com.parcial.hosting_service.dto.PictureDTO;
import com.parcial.hosting_service.models.Feature;
import com.parcial.hosting_service.models.Host;
import com.parcial.hosting_service.models.Picture;

import java.util.List;

public interface HostService {

    public Host save(HostDTO hostDTO, FeatureDTO featureDTO, PictureDTO pictureDTO);

    public List<Host> findAll();

    public Host findByName(String name);

    public Host update(String name, HostDTO hostDTO, FeatureDTO featureDTO, PictureDTO pictureDTO);

    void deleteByName(String name);

    public Host factory(HostDTO hostDTO, Feature feature, Picture picture);

}
