package com.peng1m.education.repository;

import com.peng1m.education.model.Mark;
import io.swagger.annotations.Api;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

@Api(tags = {"mark"})
@RepositoryRestResource(
        collectionResourceRel = "marks",
        path = "marks"
)
public interface MarkRepository extends PagingAndSortingRepository<Mark, Long> {
    /**
     * Only allow admin and student himself to check the score.
     *
     * @param mark_id mark_id
     * @return Mark
     */
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER') or returnObject.get().student.email == principal.username")
    @Override
    Optional<Mark> findById(Long mark_id);
}