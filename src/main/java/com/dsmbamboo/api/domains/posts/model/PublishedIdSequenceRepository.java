package com.dsmbamboo.api.domains.posts.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedIdSequenceRepository extends JpaRepository<PublishedIdSequence, Long> {
}
