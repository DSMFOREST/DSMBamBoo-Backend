package com.dsmbamboo.api.domains.questions.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentQuestion extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String question;

    @Column
    private String answer;

    @Column(name = "active_flag")
    private boolean isActive;

}
