package com.dsmbamboo.api.domains.posts.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByCategories_Name(String categoryName, Pageable pageable);
    Optional<Article> findAllByCategories_NameAndPublishedId(String categoryName, Long publishedId);

}
