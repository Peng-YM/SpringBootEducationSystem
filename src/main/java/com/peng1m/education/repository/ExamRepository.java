package com.peng1m.education.repository;

import com.peng1m.education.model.Exam;
import io.swagger.annotations.Api;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@Api(tags = {"exams"})
@RepositoryRestResource(
        collectionResourceRel = "exams",
        path = "exams")
@PreAuthorize("hasRole('USER')")
public interface ExamRepository extends PagingAndSortingRepository<Exam, Long> {
    /**
     * Only allows admin to edit exam info
     *
     * @param s   exam
     * @param <S> Exam
     * @return Exam object
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @Override
    <S extends Exam> S save(S s);

    /**
     * Only allow admin to delete exam
     *
     * @param id
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @Override
    void deleteById(Long id);
}
