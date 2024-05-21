package com.me2.repository;

import com.me2.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(@Param("email") String email);

}