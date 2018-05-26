package com.peng1m.education.repository.internal;

import com.peng1m.education.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        exported = false
)
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
