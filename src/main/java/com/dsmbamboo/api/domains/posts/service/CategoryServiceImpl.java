package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.model.Category;
import com.dsmbamboo.api.domains.posts.model.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

}
