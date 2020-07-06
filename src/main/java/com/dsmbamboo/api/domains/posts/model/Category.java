package com.dsmbamboo.api.domains.posts.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Auditable {

    public static final Long NOTICE_ID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "admin_only_flag")
    private boolean isAdminOnly;

}
