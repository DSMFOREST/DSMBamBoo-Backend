package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.exception.SequenceNotFoundException;
import com.dsmbamboo.api.domains.posts.model.PublishedIdSequence;
import com.dsmbamboo.api.domains.posts.model.PublishedIdSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.dsmbamboo.api.domains.posts.model.PublishedIdSequence.NOTICE_SEQUENCE_ID;

@Service
@RequiredArgsConstructor
public class PublishedIdGeneratorImpl implements PublishedIdGenerator {

    private final PublishedIdSequenceRepository sequenceRepository;

    @Override
    public Long getNextNoticePublishedId() {
        return sequenceRepository.findById(NOTICE_SEQUENCE_ID)
                .or(() -> Optional.of(new PublishedIdSequence(NOTICE_SEQUENCE_ID, 0L, "Notice")))
                .map(PublishedIdSequence::updateSequence)
                .map(sequenceRepository::save)
                .map(PublishedIdSequence::getSequence)
                .orElseThrow(SequenceNotFoundException::new);
    }

}
