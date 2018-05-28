package com.peng1m.education.repository;

import com.peng1m.education.model.FileResource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "resources",
        path = "resources"
)
public interface ResourceRepository extends PagingAndSortingRepository<FileResource, Long> {

}
