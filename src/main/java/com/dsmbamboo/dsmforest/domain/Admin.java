package com.dsmbamboo.dsmforest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @Email
    private String email;

    private String name;
    private String password;
}
