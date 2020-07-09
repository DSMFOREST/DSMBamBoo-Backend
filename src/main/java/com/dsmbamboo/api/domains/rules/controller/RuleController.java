package com.dsmbamboo.api.domains.rules.controller;

import com.dsmbamboo.api.domains.rules.dto.RuleResponse;
import com.dsmbamboo.api.domains.rules.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community/rules")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;

    @GetMapping
    public RuleResponse findAll() {
        return new RuleResponse(ruleService.findAll());
    }

}
