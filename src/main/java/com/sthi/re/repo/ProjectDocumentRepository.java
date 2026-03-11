package com.sthi.re.repo;

import com.sthi.re.model.ProjectDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDocumentRepository extends JpaRepository<ProjectDocument, Long> {

    List<ProjectDocument> findByProjectIdAndStatus(Long projectId, Integer status);

    List<ProjectDocument> findByProjectIdAndDocumentTypeAndStatus(
            Long projectId,
            String documentType,
            Integer status);

    List<ProjectDocument> findByProjectId(Long projectId);
}
