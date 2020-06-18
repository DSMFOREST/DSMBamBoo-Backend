package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getCategories();
    Optional<Category> findByName(String name);

}
