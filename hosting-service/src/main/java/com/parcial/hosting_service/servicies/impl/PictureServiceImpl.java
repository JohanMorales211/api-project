package com.parcial.hosting_service.servicies.impl;

import com.parcial.hosting_service.dto.PictureDTO;
import com.parcial.hosting_service.models.Picture;
import com.parcial.hosting_service.reposotories.PictureRepository;
import com.parcial.hosting_service.servicies.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    @Autowired
    private final PictureRepository pictureRepository;

    @Override
    public Picture save(PictureDTO pictureDTO) {
        Picture picture = Picture.builder()
                .name(pictureDTO.getDescription() != null ? pictureDTO.getDescription() : "Default Picture")
                .path(pictureDTO.getUrl())
                .build();
        return pictureRepository.save(picture);
    }

    @Override
    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    // @Override
    // public Picture findById(Integer id) {
    // return pictureRepository.findById(Long.valueOf(id)).orElse(null);
    // }

    @Override
    public Picture update(PictureDTO pictureDTO) {
        return pictureRepository.save(factory(pictureDTO));
    }

    @Override
    public Picture factory(PictureDTO pictureDTO) {
        Picture nuevo = Picture.builder()
                .name(pictureDTO.getName())
                .path(pictureDTO.getUrl())
                .build();

        return nuevo;
    }
}
