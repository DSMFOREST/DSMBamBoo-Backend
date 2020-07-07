package com.dsmbamboo.api.domains.posts.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByIsActiveTrueAndType(ArticleType type, Pageable pageable);
    Optional<Article> findByIsActiveTrueAndTypeAndPublishedId(ArticleType type, Long publishedId);
    Optional<Article> findByIsActiveTrueAndTypeAndId(ArticleType type, Long id);

}
