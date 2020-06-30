package com.dsmbamboo.api.domains.images.controller;

import com.dsmbamboo.api.domains.images.dto.ImageResponse;
import com.dsmbamboo.api.domains.images.dto.UploadImageResponse;
import com.dsmbamboo.api.domains.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public UploadImageResponse uploadImages(@RequestParam("images") MultipartFile[] images) {
        return new UploadImageResponse(
                imageService.uploadAll(images)
                        .stream()
                        .map(ImageResponse::new)
                        .collect(Collectors.toList())
        );
    }

}
