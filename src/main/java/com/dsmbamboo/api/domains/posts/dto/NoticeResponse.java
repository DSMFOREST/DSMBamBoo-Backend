package com.dsmbamboo.api.domains.posts.dto;

import com.dsmbamboo.api.domains.images.model.Image;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.Category;
import com.dsmbamboo.api.utils.TimeCalculator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private String facebookLink;
    private List<String> categories;
    private List<String> images;

    @JsonFormat(timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private String recentCreatedAt;

    public NoticeResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.facebookLink = article.getFacebookLink();
        this.categories = article.getCategories()
                .stream().map(Category::getName).collect(Collectors.toList());
        this.images = article.getImages()
                .stream().map(Image::getUrl).collect(Collectors.toList());
        this.createdAt = article.getCreatedAt();
        this.recentCreatedAt = TimeCalculator.toRecentKorean(article.getCreatedAt());
    }

}
