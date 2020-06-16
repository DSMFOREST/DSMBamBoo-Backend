package com.dsmbamboo.api.domains.posts.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import com.dsmbamboo.api.domains.images.model.Image;
import com.dsmbamboo.api.domains.users.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long publishedId;

    @Column(nullable = false)
    private String title;

    @Size(max = 2000)
    @Column(nullable = false)
    private String content;

    @Column
    private String facebookLink;

    @OneToMany
    private List<Category> categories;

    @ManyToOne
    private User approver;

    @Column(nullable = false, name = "active_flag")
    private boolean isActive;

    @OneToMany
    @OrderColumn(name = "sequence_id")
    private List<Image> images;

}
