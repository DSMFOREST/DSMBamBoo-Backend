package com.dsmbamboo.api.domains.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticleRequest {

    @Size(max = 255)
    private String title;

    @Size(max = 2000)
    private String content;

    @NotEmpty
    private List<Long> categories;

    @NotNull
    private List<Long> images;

}
