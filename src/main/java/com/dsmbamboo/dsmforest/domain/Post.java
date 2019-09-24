package com.dsmbamboo.dsmforest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id private long id;

    private String title;
    private String contents;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @Nullable private LocalDateTime approvedTime;
    @Nullable private String uploadedLink;

    @Nullable private String fileUrl;
    @Nullable private String category;

}
