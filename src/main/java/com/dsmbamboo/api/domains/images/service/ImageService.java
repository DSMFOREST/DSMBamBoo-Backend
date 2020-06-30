package com.dsmbamboo.api.domains.images.service;

import com.dsmbamboo.api.domains.images.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<Image> uploadAll(MultipartFile[] images);

}
