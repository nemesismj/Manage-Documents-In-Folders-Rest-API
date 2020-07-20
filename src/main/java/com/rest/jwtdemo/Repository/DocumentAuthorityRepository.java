package com.rest.jwtdemo.Repository;

import com.rest.jwtdemo.Model.Document;
import com.rest.jwtdemo.Model.DocumentAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentAuthorityRepository extends JpaRepository<DocumentAuthority, Long> {
    void deleteByFileName(String username);
}
