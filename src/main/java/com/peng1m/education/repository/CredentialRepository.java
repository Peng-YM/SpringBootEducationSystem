package com.peng1m.education.repository;

import com.peng1m.education.model.Credential;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CredentialRepository extends PagingAndSortingRepository<Credential, Long> {
    Credential findByUsername(String username);
}
