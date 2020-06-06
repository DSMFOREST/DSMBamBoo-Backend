package com.dsmbamboo.api.domains.rules.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityRule extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(name = "active_flag")
    private boolean isActive;

}
