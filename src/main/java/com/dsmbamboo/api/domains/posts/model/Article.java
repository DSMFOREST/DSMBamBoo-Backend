package com.dsmbamboo.api.domains.posts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Builder
@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {

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

    @Column
    private String link;

    @Column(nullable = false)
    private boolean isVisible;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne
    private Draft draft;

    @ManyToOne
    private User user;

}
