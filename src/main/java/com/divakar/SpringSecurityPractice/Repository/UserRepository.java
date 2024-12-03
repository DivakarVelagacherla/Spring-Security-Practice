package com.divakar.SpringSecurityPractice.Repository;

import com.divakar.SpringSecurityPractice.Entity.User;
import com.divakar.SpringSecurityPractice.Enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByEmail(String email);

    User findByRole(Role role);
}
