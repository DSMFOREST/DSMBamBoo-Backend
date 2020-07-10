package com.dsmbamboo.api.domains.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionRequest {

    @NotEmpty
    private String question;

    @NotEmpty
    private String answer;

}
