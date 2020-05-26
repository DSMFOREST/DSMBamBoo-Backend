package com.dsmbamboo.api.domains.notices.model;

import com.dsmbamboo.api.domains.posts.model.Category;
import com.dsmbamboo.api.domains.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

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

    @ManyToOne
    private User writtenBy;

    @Column
    private String link;

    @Column(nullable = false)
    private boolean isVisible;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
