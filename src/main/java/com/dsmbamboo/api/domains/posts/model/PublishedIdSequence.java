package com.dsmbamboo.api.domains.posts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PublishedIdSequence {

    public static final Long NOTICE_SEQUENCE_ID = 1L;
    public static final Long ARTICLE_SEQUENCE_ID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long sequence;

    @Column(nullable = false)
    private String description;

    public PublishedIdSequence updateSequence() {
        this.sequence += 1;
        return this;
    }

}
