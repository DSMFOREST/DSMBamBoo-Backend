package com.dsmbamboo.api.domains.images.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageResponse {

    private List<ImageResponse> images;

}
