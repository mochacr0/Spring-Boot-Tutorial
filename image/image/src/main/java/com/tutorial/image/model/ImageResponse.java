package com.tutorial.image.model;

import lombok.Data;

@Data
public class ImageResponse {
    private String id;
    private String name;
    private String contentType;
    private Long size;
    private String url;
}

