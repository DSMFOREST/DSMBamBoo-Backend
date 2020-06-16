package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.model.Category;
import com.dsmbamboo.api.domains.posts.model.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<String> getCategories() {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN");
        List<Category> categories = (isAdmin) ? findAll() : findAllByAdminOnlyFalse();
        return categories
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllByAdminOnlyFalse() {
        return categoryRepository.findAllByIsAdminOnlyFalse();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

}
