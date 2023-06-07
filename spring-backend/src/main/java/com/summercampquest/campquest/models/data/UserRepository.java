package com.summercampquest.campquest.models.data;

import com.summercampquest.campquest.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByuserName(String userName);
}
