package com.dsmbamboo.api.domains.images.dto;

import com.dsmbamboo.api.domains.images.model.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private Long id;
    private String url;

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
    }

}
