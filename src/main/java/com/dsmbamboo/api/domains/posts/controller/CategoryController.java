package com.dsmbamboo.api.domains.posts.controller;

import com.dsmbamboo.api.domains.posts.dto.CategoryResponse;
import com.dsmbamboo.api.domains.posts.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryService.getCategories()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

}
