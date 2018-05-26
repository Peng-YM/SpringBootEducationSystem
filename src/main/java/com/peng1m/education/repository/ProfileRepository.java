package com.peng1m.education.repository;

import com.peng1m.education.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(
        collectionResourceRel = "profiles",
        path = "profiles"
)
@PreAuthorize("hasRole('USER')")
public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
