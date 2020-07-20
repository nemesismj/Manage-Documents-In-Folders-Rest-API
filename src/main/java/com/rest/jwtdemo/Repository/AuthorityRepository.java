package com.rest.jwtdemo.Repository;

import com.rest.jwtdemo.Model.Authority;
import com.rest.jwtdemo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuthority(String username);
    Authority findByUsername(String username);
    void deleteByUsername(String username);
}
