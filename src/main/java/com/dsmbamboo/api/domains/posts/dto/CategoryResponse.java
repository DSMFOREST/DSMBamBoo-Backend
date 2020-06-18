package com.dsmbamboo.api.domains.posts.dto;

import com.dsmbamboo.api.domains.posts.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

}
