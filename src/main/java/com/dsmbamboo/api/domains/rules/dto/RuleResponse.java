package com.dsmbamboo.api.domains.rules.dto;

import com.dsmbamboo.api.domains.rules.model.CommunityRule;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RuleResponse {

    private List<String> rules;

    public RuleResponse(List<CommunityRule> rules) {
        this.rules = rules.stream()
                .map(CommunityRule::getContents)
                .collect(Collectors.toList());
    }

}
