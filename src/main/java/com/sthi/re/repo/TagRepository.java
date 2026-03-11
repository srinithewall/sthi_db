package com.sthi.re.repo;

import com.sthi.re.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByIsActiveTrue();

    Optional<Tag> findByTagNameIgnoreCaseAndIsActiveTrue(String tagName);

    List<Tag> findByTagIdIn(List<Long> tagIds);

    List<Tag> findByTagTypeAndIsActiveTrueOrderByDisplayOrderAsc(String tagType);
}