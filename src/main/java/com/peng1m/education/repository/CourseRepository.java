package com.peng1m.education.repository;

import com.peng1m.education.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "courses",
        path = "courses")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}