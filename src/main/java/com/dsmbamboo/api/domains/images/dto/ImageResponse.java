package com.dsmbamboo.api.domains.images.dto;

import com.dsmbamboo.api.domains.images.model.Image;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private static final String IMAGE_URL_PREFIX = "https://img.dsmbamboo.com/";

    private Long id;
    private String url;

    @JsonGetter("url")
    public String getUrl() {
        return IMAGE_URL_PREFIX.concat(url);
    }

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
    }

}
