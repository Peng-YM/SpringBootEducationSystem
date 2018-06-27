package com.peng1m.education.repository;

import com.peng1m.education.model.Mark;
import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@Api(tags = {"mark"})
@RepositoryRestResource(
        collectionResourceRel = "marks",
        path = "marks"
)
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
public interface MarkRepository extends PagingAndSortingRepository<Mark, Long> {
    /**
     * Only allow admin and student himself to check the score.
     *
     * @param mark_id mark_id
     * @return Mark
     */
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER') or returnObject.get().user.email == principal.username")
    @Override
    Optional<Mark> findById(Long mark_id);

    @Query("SELECT m FROM Mark m WHERE m.user = :userId")
    List<Mark> findByUser(@Param("userId") String userId);
}