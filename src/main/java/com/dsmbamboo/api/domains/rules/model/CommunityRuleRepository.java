package com.dsmbamboo.api.domains.rules.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRuleRepository extends JpaRepository<CommunityRule, Long> {
}
