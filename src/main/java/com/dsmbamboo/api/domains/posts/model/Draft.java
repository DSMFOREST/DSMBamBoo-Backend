package com.dsmbamboo.api.domains.posts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Draft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ElementCollection
    private List<String> images;

    @ManyToMany
    private List<Category> categories;

    @Column(nullable = false)
    private boolean isVisible;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Article toArticle() {
        return Article.builder()
                .title(title)
                .content(content)
                .images(new ArrayList<>(images))
                .categories(new ArrayList<>(categories))
                .isVisible(true)
                .createdAt(LocalDateTime.now())
                .draft(this)
                .build();
    }

}
