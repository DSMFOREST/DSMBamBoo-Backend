package com.dsmbamboo.api.domains.images.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image extends Auditable {

    @Id
    private Long articleId;

    @Column
    private Integer sequence_id;

    @Column
    private String url;

}
