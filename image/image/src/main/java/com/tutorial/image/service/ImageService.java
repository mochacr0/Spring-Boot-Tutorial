package com.tutorial.image.service;

import com.tutorial.image.model.Image;
import com.tutorial.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public Image save(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        image.setContentType(file.getContentType());
        image.setData(file.getBytes());
        image.setSize(file.getSize());
        return imageRepository.save(image);
    }
    public Optional<Image> getImage(String id) {
        return imageRepository.findById(id);
    }
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
