package com.namee.api.repository;

import com.namee.api.model.security.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by namee on 2016. 6. 24..
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
