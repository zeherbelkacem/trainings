package com.fms.trainings.service;

import com.fms.trainings.entities.ImageModel;
import com.fms.trainings.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public ImageModel saveImage(ImageModel imageModel) {
        System.out.println("images: "+imageModel.getName());
        System.out.println("images: "+imageModel.getType());
        System.out.println("images: "+imageModel.getBytePicture().length);
        return imageRepository.save(imageModel);
    }
}
