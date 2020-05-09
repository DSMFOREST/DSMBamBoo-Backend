package com.dsmbamboo.api.domains.posts.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftRepository extends CrudRepository<Draft, Long> {
}
