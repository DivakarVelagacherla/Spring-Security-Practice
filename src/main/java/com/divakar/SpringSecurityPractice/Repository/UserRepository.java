package com.divakar.SpringSecurityPractice.Repository;

import com.divakar.SpringSecurityPractice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
