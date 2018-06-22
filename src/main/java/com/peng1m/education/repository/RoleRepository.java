package com.peng1m.education.repository;

import com.peng1m.education.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long> {
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Override
    <S extends Role> S save(S entity);
}
