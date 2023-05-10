package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}