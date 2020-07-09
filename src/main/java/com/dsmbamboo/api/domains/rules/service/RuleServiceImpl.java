package com.dsmbamboo.api.domains.rules.service;

import com.dsmbamboo.api.domains.rules.model.CommunityRule;
import com.dsmbamboo.api.domains.rules.model.CommunityRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final CommunityRuleRepository ruleRepository;

    @Override
    public List<CommunityRule> findAll() {
        return ruleRepository.findAllByIsActiveTrueOrderByIdAsc();
    }

}
