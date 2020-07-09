package com.dsmbamboo.api.domains.rules.service;

import com.dsmbamboo.api.domains.rules.model.CommunityRule;

import java.util.List;

public interface RuleService {

    List<CommunityRule> findAll();

}
