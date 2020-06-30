package com.dsmbamboo.api.domains.images.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dsmbamboo.api.domains.images.exception.UnprocessableImageException;
import com.dsmbamboo.api.domains.images.model.Image;
import com.dsmbamboo.api.domains.images.model.ImageRepository;
import com.dsmbamboo.api.domains.users.security.AuthenticationFacade;
import com.dsmbamboo.api.domains.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class S3ImageServiceImpl implements ImageService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final UserService userService;
    private final ImageRepository imageRepository;
    private final AuthenticationFacade authFacade;

    @Override
    public List<Image> uploadAll(MultipartFile[] images) {
        return userService.findByUsername(authFacade.getUsername())
                .map(user -> Stream.of(images)
                        .filter(this::isImageFile)
                        .map(image -> this.upload(user.getId(), image))
                        .map(this::saveToImage)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }

    private boolean isImageFile(MultipartFile image) {
        List<String> uploadableExtensions = List.of("jpeg", "png", "gif");
        String[] keywords = image.getContentType().split("/");
        String mediaType = keywords[0];
        String imageType = keywords[1];
        return mediaType.equals("image") && uploadableExtensions.contains(imageType);
    }

    private String upload(Long userId, MultipartFile image) {
        System.out.println(image.getOriginalFilename());
        String filename = userId + "/" + UUID.randomUUID().toString() + "." + image.getContentType().substring(6);
        try {
            amazonS3.putObject(new PutObjectRequest(bucket, filename, image.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new UnprocessableImageException();
        }
        return filename;
    }

    private Image saveToImage(String fileName) {
        return imageRepository.save(new Image(0L, fileName));
    }

}
