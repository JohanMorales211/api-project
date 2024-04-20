package com.parcial.hosting_service.servicies;

import com.parcial.hosting_service.dto.PictureDTO;
import com.parcial.hosting_service.models.Picture;

import java.util.List;

public interface PictureService {


    public Picture save(PictureDTO pictureDTO);

    public List<Picture> findAll();

    public Picture findById(Integer id);

    public Picture update(PictureDTO pictureDTO);

    public Picture factory(PictureDTO pictureDTO);
}
