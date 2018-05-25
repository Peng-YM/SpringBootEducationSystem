package com.peng1m.education.repository;

import com.peng1m.education.model.Exam;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "exams",
        path = "exams")
public interface ExamRepository extends PagingAndSortingRepository<Exam, Long> {
}
