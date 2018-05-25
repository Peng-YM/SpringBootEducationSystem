package com.peng1m.education.repository;

import com.peng1m.education.model.Mark;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "marks",
        path = "marks"
)
public interface MarkRepository extends PagingAndSortingRepository<Mark, Long> {

}