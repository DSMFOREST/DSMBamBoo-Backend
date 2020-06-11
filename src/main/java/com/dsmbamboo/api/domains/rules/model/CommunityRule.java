package com.dsmbamboo.api.domains.rules.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityRule extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(name = "active_flag")
    private boolean isActive;

}
