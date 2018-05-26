package com.peng1m.education.repository.internal;

import com.peng1m.education.model.Credential;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        exported = false
)
public interface CredentialRepository extends PagingAndSortingRepository<Credential, Long> {
    Credential findByUsername(String username);
}
