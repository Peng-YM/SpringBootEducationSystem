package com.peng1m.education.repository;

import com.peng1m.education.model.Course;
import io.swagger.annotations.Api;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Course list are public resource, all user can access them
 */
@RepositoryRestResource(
        collectionResourceRel = "courses",
        path = "courses")
@Api(tags = {"courses"})
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    /**
     * Find course by course code
     * Example usage: api/courses/search/findByCourseCode?code=CS303
     *
     * @param courseCode code
     * @return Course
     */
    Course findByCourseCode(@Param("code") String courseCode);

    /**
     * Only allow admin to edit the course information
     *
     * @param s   course
     * @param <S> Course class
     * @return Course
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Override
    <S extends Course> S save(S s);

    /**
     * Only allow admin to delete the course
     *
     * @param aLong
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Override
    void deleteById(Long aLong);
}