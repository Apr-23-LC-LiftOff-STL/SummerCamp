package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<User> findByuserName(String username);

//    Boolean existsByUsername(String username);

//    Boolean existsByEmail(String userEmail);

//    User findByEmailAndPassword(String userEmail, String password);

    User findByuserEmail(String userEmail);

    User findUserByToken(String token);

    User findByuserName(String userName);

}