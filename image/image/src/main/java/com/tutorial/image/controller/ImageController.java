package com.tutorial.image.controller;

import com.tutorial.image.model.Image;
import com.tutorial.image.model.ImageResponse;
import com.tutorial.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/api")
    public ResponseEntity<?> hello() {
        return ResponseEntity.status(200).body(imageService
                .getAllImages()
                .stream()
                .map(this::convertFromImageToResponse)
                .collect(Collectors.toList()));
    }

    @PostMapping("/api/file")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            Image image = imageService.save(file);
            //return ResponseEntity.status(200).body(String.format("Uploaded successfully: %s", file.getOriginalFilename()));
            return ResponseEntity.status(200).body(this.convertFromImageToResponse(image));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(String.format("Could not upload file: %s", file.getOriginalFilename()));
        }
    }

    private ImageResponse convertFromImageToResponse(Image image) {
        ImageResponse imageResponse = new ImageResponse();
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/file/")
                .path(image.getId()).toUriString();
        imageResponse.setId(image.getId());
        imageResponse.setName(image.getName());
        imageResponse.setUrl(url);
        imageResponse.setSize(image.getSize());
        imageResponse.setContentType(image.getContentType());
        return imageResponse;
    }

    @GetMapping("/api/file")
    public List<ImageResponse> getAllImages() {
        return imageService
                .getAllImages()
                .stream()
                .map(this::convertFromImageToResponse)
                .collect(Collectors.toList());
    }
    @GetMapping("api/file/{id}")
    public ResponseEntity<byte []> getImageById(@PathVariable String id) {
        Optional<Image> imageEntityOptional = imageService.getImage(id);
        if (!imageEntityOptional.isPresent()) {
            return ResponseEntity.status(404).build();
        }
        Image image = imageEntityOptional.get();
        return ResponseEntity
                .status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(image.getData());
    }


}
